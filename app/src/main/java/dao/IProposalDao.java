package dao;

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

}
