package webservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import dao.IAnnouncementDao;
import models.User;
import services.IResult;
import models.Announcement;

import com.example.anderson.expressdelivery.R;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
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

        ParseFile imageFile = new ParseFile(announcement.getUser() + announcement.getTitle() +
                ".png", image);
        imageFile.saveInBackground();

        ParseObject parseObject = new ParseObject("Announcement");
        parseObject.put("User", announcement.getUser());
        parseObject.put("Title", announcement.getTitle());
        parseObject.put("Endereco", announcement.getEndereco());
        parseObject.put("Description", announcement.getDescription());
        parseObject.put("Telefone", announcement.getTelefone());
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

    }

    @Override
    public void update(Announcement announcement, IResult<Announcement> result) {

    }

    @Override
    public void remove(Announcement announcement, IResult<Announcement> result) {

    }

    @Override
    public void get(Announcement announcement, IResult<Announcement> result) {


    }


    @Override
    public void getAll(final IResult<Announcement> result) {
        final List<Announcement> announcementsList = new ArrayList<Announcement>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Announcement");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    result.onError(e.getMessage());
                } else {
                    for (ParseObject parseObject : objects) {
                        User user = (User) parseObject.get("User");
                        String title = parseObject.get("Title").toString();
                        String endereco = parseObject.get("Endereco").toString();
                        String description = parseObject.get("Description").toString();
                        String telefone = parseObject.get("Description").toString();
                        final Announcement announcement = new Announcement(user, title, description,
                                endereco, telefone, null);
                        ParseFile imageFile = (ParseFile) parseObject.get("Picture");
                        imageFile.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (e != null) {
                                    result.onError(e.getMessage());
                                } else {
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    announcement.setPicture(bitmap);
                                    announcementsList.add(announcement);
                                }
                            }
                        });
                    }
                    result.onSuccess(announcementsList);
                }
            }
        });
    }
}
