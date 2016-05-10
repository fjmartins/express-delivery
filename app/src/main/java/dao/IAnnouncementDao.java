package dao;

import java.util.List;

import models.Announcement;
import models.User;
import services.IResult;
import services.IResultGeneric;

/**
 * Created by kleber on 21/04/16.
 */
public interface IAnnouncementDao {

    void insert(Announcement announcement, IResult<Announcement> result);
    void update(Announcement announcement, IResult<Announcement> result);
    void remove(Announcement announcement, IResult<Announcement> result);
    void get(int size, int skip , IResult<Announcement> result);
    void getAll(IResult<Announcement> result);
    void getMy(User user, IResult<Announcement> result);
    void getSize(IResultGeneric result);

}
