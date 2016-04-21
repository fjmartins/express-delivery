package webservices;

import java.util.List;

import dao.IAnnouncementDao;
import dao.IResult;
import models.Announcement;

/**
 * Created by kleber on 21/04/16.
 */
public class AnnouncementWS implements IAnnouncementDao {

    @Override
    public void insert(Announcement announcement, IResult<Announcement> result) {
        
    }

    @Override
    public void update(Announcement announcement, IResult<Announcement> result) {

    }

    @Override
    public void remove(Announcement announcement, IResult<Announcement> result) {

    }

    @Override
    public Announcement get(Announcement announcement, IResult<Announcement> result) {
        return null;
    }

    @Override
    public List<Announcement> getAll(IResult<Announcement> result) {
        return null;
    }
}
