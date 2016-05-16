package utils;

import android.graphics.Bitmap;
import android.os.Bundle;

import models.Announcement;

/**
 * Created by morte on 16/04/2016.
 */
public class AnnouncementUtils {

    public static Bundle sendExtras(Announcement announcement) {
        Bundle extrasOut = new Bundle();

        extrasOut.putString("id", announcement.getId());
        extrasOut.putString("tittle", announcement.getTitle());
        extrasOut.putString("description", announcement.getDescription());
        extrasOut.putString("address", announcement.getAddress());
        extrasOut.putParcelable("picture", announcement.getPicture());
        extrasOut.putString("phone", announcement.getPhone());
        extrasOut.putString("username", announcement.getUser());

        return extrasOut;
    }

    public static Announcement getExtras(Bundle extras) {
        Announcement announcement = new Announcement(
                extras.get("username").toString(), extras.get("tittle").toString(),
                extras.get("description").toString(), extras.get("address").toString(),
                extras.get("phone").toString(), (Bitmap) extras.getParcelable("picture"));

        announcement.setId(extras.get("id").toString());

        return announcement;
    }
}