package webservices;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import dao.IProposalDao;
import models.Announcement;
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
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Proposal");
        query.getInBackground(proposal.getId(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                object.put("IsAccept", true);
            }
        });
    }

    @Override
    public void getAll(User user, IResult result) {

    }

    @Override
    public void getByAnnouncement(Announcement announcement, IResult<Proposal> result) {
        List<ParseObject> parseObjectList = new ArrayList<>();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Proposal");
        query.whereEqualTo("AnnouncementId", announcement.getId());

        try {
            parseObjectList = query.find();
        } catch (ParseException e) {
            result.onError(e.getMessage());
        }

        List<Proposal> ProposalList = new ArrayList<Proposal>();
        Proposal proposal;

        for (ParseObject parseObject : parseObjectList) {

            String userFrom = parseObject.get("UserFrom").toString();
            String title = parseObject.get("Title").toString();
            String description = parseObject.get("Description").toString();
            String value = parseObject.get("Value").toString();
            String announcementId = parseObject.get("AnnouncementId").toString();
            String id = parseObject.getObjectId();

            proposal = new Proposal(title, description, Double.parseDouble(value));
            proposal.setUserFrom(userFrom);
            proposal.setAnnouncementId(announcementId);
            proposal.setAccept(false);
            proposal.setId(id);

            ProposalList.add(proposal);
        }

        result.onSuccess(ProposalList);
    }
}
