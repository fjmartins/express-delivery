package dao;

import models.Announcement;
import models.Proposal;
import models.User;
import services.IResult;

/**
 * Created by kleber on 10/05/16.
 */
public interface IProposalDao {

    void send(Proposal proposal, IResult result);
    void analyze(Proposal proposal, IResult result);
    void getAll(User user, IResult result);
    void getByAnnouncement(Announcement announcement, IResult<Proposal> result);

}
