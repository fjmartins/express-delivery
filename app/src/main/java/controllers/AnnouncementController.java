package controllers;

import java.util.List;

import dao.IAnnouncementDao;
import models.Announcement;
import services.IResult;
import webservices.AnnouncementParse;

/**
 * Created by kleber on 26/04/16.
 */
public class AnnouncementController {

    private static IAnnouncementDao announcementDao = new AnnouncementParse();

    public static void insert(final Announcement announcement, final IResult<Announcement> result) {
        announcementDao.insert(announcement, new IResult<Announcement>() {
            @Override
            public void onSuccess(List<Announcement> list) {

            }

            @Override
            public void onSuccess(Announcement obj) {
                result.onSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }

    public static void update(Announcement announcement, final IResult<Announcement> result){
        announcementDao.update(announcement, new IResult<Announcement>() {
            @Override
            public void onSuccess(List<Announcement> list) {

            }

            @Override
            public void onSuccess(Announcement obj) {
                result.onSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }

    public static void getAll(int limit, int size, final IResult<Announcement> result) {

        announcementDao.getAll(limit , size , new IResult<Announcement>() {
            @Override
            public void onSuccess(List<Announcement> list) {
                result.onSuccess(list);
            }

            @Override
            public void onSuccess(Announcement obj) {

            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }

}
