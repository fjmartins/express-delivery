package webservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import dao.IAnnouncementDao;
import services.IResult;
import models.Announcement;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.GetCallback;


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
                        result.onSuccess(announcement);
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
    public void remove(Announcement announcement, final IResult<Announcement> result) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Announcement");
        query.getInBackground(announcement.getId(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            result.onError(e.getMessage());
                        }
                    });
                } else {
                    result.onError(e.getMessage());
                }
            }
        });

    }

    @Override
    public void get(int size, int skip, IResult<Announcement> result) {
        List<ParseObject> parseObjectList = new ArrayList<>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Announcement");
        query.orderByDescending("createdAt");
        query.setSkip(skip);
        query.setLimit(size);

        try {
            parseObjectList = query.find();
        } catch (ParseException e) {
            result.onError(e.getMessage());
        }

        List<Announcement> announcementsList = new ArrayList<Announcement>();
        Announcement announcement;

        for (ParseObject parseObject : parseObjectList) {

            String user = parseObject.get("User").toString();
            String title = parseObject.get("Title").toString();
            String address = parseObject.get("Address").toString();
            String description = parseObject.get("Description").toString();
            String phone = parseObject.get("Phone").toString();
            String id = parseObject.getObjectId();

            ParseFile imageFile = (ParseFile) parseObject.get("Picture");
            Bitmap picture = null;
            try {
                byte[] data = imageFile.getData();
                picture = BitmapFactory.decodeByteArray(data, 0, data.length);
            } catch (Exception erro) {
                result.onError(erro.getMessage());
            }

            announcement = new Announcement(user, title, description, address, phone, picture);
            announcement.setId(id);
            announcementsList.add(announcement);
        }

        result.onSuccess(announcementsList);
    }

    @Override
    public void getAll(final IResult<Announcement> result) {
        final List<Announcement> announcementsList = new ArrayList<Announcement>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Announcement");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for (ParseObject parseObject : objects) {
                    String user = parseObject.get("User").toString();
                    String title = parseObject.get("Title").toString();
                    String address = parseObject.get("Address").toString();
                    String description = parseObject.get("Description").toString();
                    String phone = parseObject.get("Phone").toString();
                    String id = parseObject.getObjectId();
                    final Announcement announcement = new Announcement(user, title, description,
                            address, phone, null);
                    announcement.setId(id);
                    ParseFile imageFile = (ParseFile) parseObject.get("Picture");
                    try {
                        byte[] data = imageFile.getData();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        announcement.setPicture(bitmap);
                    } catch (Exception erro) {
                        result.onError(erro.getMessage());
                    }
                    announcementsList.add(announcement);
                }
            }
        });
        result.onSuccess(announcementsList);
    }
}
