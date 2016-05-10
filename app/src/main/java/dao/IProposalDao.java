package dao;

import models.Proposal;
import services.IResult;

/**
 * Created by kleber on 10/05/16.
 */
public interface IProposalDao {

    void send(Proposal proposal, IResult result);
    void analyze(Proposal proposal, IResult result);
    void getAll(IResult result);

}
