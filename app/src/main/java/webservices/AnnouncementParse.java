package webservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import dao.IAnnouncementDao;
import services.IResult;
import models.Announcement;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.ParseFile;
import com.parse.ParseQuery;


/**
 * Created by kleber on 21/04/16.
 */
public class AnnouncementParse implements IAnnouncementDao {

    @Override
    public void insert(final Announcement announcement, final IResult<Announcement> result) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        announcement.getPicture().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();

        final ParseFile imageFile = new ParseFile(announcement.getUser() + System.currentTimeMillis() +
                ".png", image);
        imageFile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    ParseObject parseObject = new ParseObject("Announcement");
                    parseObject.put("User", announcement.getUser());
                    parseObject.put("Title", announcement.getTitle());
                    parseObject.put("Address", announcement.getAddress());
                    parseObject.put("Description", announcement.getDescription());
                    parseObject.put("Phone", announcement.getPhone());
                    parseObject.put("Picture", imageFile);

                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                result.onError(e.getMessage());
                            } else {
                                result.onSuccess(announcement);
                            }
                        }
                    });
                } else {
                    result.onError(e.getMessage());
                }
            }
        });
    }

    @Override
    public void update(final Announcement announcement, final IResult<Announcement> result) {

        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        announcement.getPicture().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();

        final ParseFile imageFile = new ParseFile(announcement.getUser() + System.currentTimeMillis() +
                ".png", image);
        imageFile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Announcement");
                    ParseObject parseObject = null;
                    try {
                        parseObject = query.get(announcement.getId());
                    } catch (ParseException erro) {
                        result.onError(erro.getMessage());
                    }
                    parseObject.put("User", announcement.getUser());
                    parseObject.put("Title", announcement.getTitle());
                    parseObject.put("Address", announcement.getAddress());
                    parseObject.put("Description", announcement.getDescription());
                    parseObject.put("Phone", announcement.getPhone());
                    parseObject.put("Picture", imageFile);

                    try {
                        parseObject.save();
                    } catch (ParseException erro) {
                        erro.printStackTrace();
                    }

                } else {
                    result.onError(e.getMessage());
                }
            }
        });

    }

    @Override
    public void remove(Announcement announcement, IResult<Announcement> result) {

    }

    @Override
    public void get(Announcement announcement, IResult<Announcement> result) {


    }

    @Override
    public void getAll(int limit, final IResult<Announcement> result) {
        final List<Announcement> announcementsList = new ArrayList<Announcement>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Announcement");

        query.orderByDescending("createdAt");
        if ((limit > 0) ? true : false) {
            query.setLimit(limit);
        }

        try {
            List<ParseObject> objects = query.find();
            for (ParseObject parseObject : objects) {
                String user = parseObject.get("User").toString();
                String title = parseObject.get("Title").toString();
                String endereco = parseObject.get("Address").toString();
                String description = parseObject.get("Description").toString();
                String telefone = parseObject.get("Phone").toString();
                String id = parseObject.getObjectId();
                final Announcement announcement = new Announcement(user, title, description,
                        endereco, telefone, null);
                announcement.setId(id);
                ParseFile imageFile = (ParseFile) parseObject.get("Picture");
                byte[] data = imageFile.getData();
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                announcement.setPicture(bitmap);
                announcementsList.add(announcement);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result.onSuccess(announcementsList);
    }
}
