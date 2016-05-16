package utils;

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


}