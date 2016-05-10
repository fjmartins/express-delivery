package webservices;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import dao.IProposalDao;
import models.Proposal;
import models.User;
import services.IResult;

/**
 * Created by kleber on 10/05/16.
 */
public class ProposalParse implements IProposalDao {
    @Override
    public void send(final Proposal proposal, final IResult result) {
        ParseObject parseObject = new ParseObject("Proposal");
        parseObject.put("UserFrom", proposal.getUserFrom());
        parseObject.put("Title", proposal.getTitle());
        parseObject.put("Description", proposal.getDescription());
        parseObject.put("Value", proposal.getValue());
        parseObject.put("IsAccept", proposal.isAccept());
        parseObject.put("AnnouncementId", proposal.getAnnouncementId());

        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    result.onError(e.getMessage());
                } else {
                    result.onSuccess(proposal);
                }
            }
        });
    }

    @Override
    public void analyze(Proposal proposal, IResult result) {

    }

    @Override
    public void getAll(User user, IResult result) {

    }
}
