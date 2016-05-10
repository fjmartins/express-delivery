package controllers;

import java.util.List;

import dao.IAnnouncementDao;
import models.Announcement;
import services.IResult;
import services.IResultGeneric;
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

    public static void update(Announcement announcement, final IResult<Announcement> result) {
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

    public static void get(int size, int skip, final IResult<Announcement> result) {
        announcementDao.get(size, skip, new IResult<Announcement>() {
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

    public static void remove(Announcement announcement, final IResult<Announcement> result) {
        announcementDao.remove(announcement, new IResult<Announcement>() {
            @Override
            public void onSuccess(List<Announcement> list) {

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

    public static void getAll(final IResult<Announcement> result) {

        announcementDao.getAll(new IResult<Announcement>() {
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

    public static void getSize(final IResultGeneric result) {
        announcementDao.getSize(new IResultGeneric() {
            @Override
            public void onSuccess(Integer value) {
                result.onSuccess(value);
            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }

}
