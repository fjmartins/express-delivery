package views;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.example.anderson.expressdelivery.R;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import controllers.AnnouncementController;
import models.Announcement;
import services.IResult;
import utils.AnnouncementUtils;

/**
 * Refactoring morte on 16/05/16.
 */

public class AnnouncementDetailsActivity extends GenericActivity {

    private TextView title, description, phone, address;
    private ImageView picture;
    private Announcement announcement;

    private FloatingActionsMenu fbMenu;
    private FloatingActionButton fbListProposal, fbEditAnnouncement,
            fbDeleteAnnouncement, fbProposalSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_detail_activity);

        overridePendingTransition(R.anim.register_activity_enter, R.anim.main_activity_exit);

        this.title = (TextView) findViewById(R.id.txt_announcement_detail_activity_title);
        this.description = (TextView) findViewById(R.id.txt_announcement_detail_activity_description);
        this.phone = (TextView) findViewById(R.id.txt_announcement_detail_activity_phone);
        this.address = (TextView) findViewById(R.id.txt_announcement_detail_activity_address);
        this.picture = (ImageView) findViewById(R.id.img_announcement_detail_activity_picture);

        this.fbMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        this.fbListProposal = (FloatingActionButton) findViewById(R.id.fbListProposal);
        this.fbEditAnnouncement = (FloatingActionButton) findViewById(R.id.fbEditDetAnunc);
        this.fbDeleteAnnouncement = (FloatingActionButton) findViewById(R.id.fbdelete_announcment);
        this.fbProposalSubmit = (FloatingActionButton) findViewById(R.id.fbProposalSubmit);

        loadData();
    }


    private void loadData() {

        this.announcement = AnnouncementUtils.getExtras(getIntent().getBundleExtra("bundle"));

        this.title.setText(announcement.getTitle());
        this.description.setText(announcement.getDescription());
        this.phone.setText(announcement.getPhone());
        this.address.setText(announcement.getAddress());
        this.picture.setImageBitmap(announcement.getPicture());

        if (!this.getUsername().equals(announcement.getUser())) {
            this.fbEditAnnouncement.setVisibility(View.INVISIBLE);
            this.fbDeleteAnnouncement.setVisibility(View.INVISIBLE);
            this.fbListProposal.setVisibility(View.INVISIBLE);
            this.fbMenu.setVisibility(View.INVISIBLE);

        } else {
            this.fbProposalSubmit.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public void updateAnnouncement(View v) {
        this.isUserAuth(this);
        redirect(this,
                AnnouncementRegisterActivity.class,
                AnnouncementUtils.sendExtras(this.announcement));
    }

    public void showProposal(View v) {
        this.isUserAuth(this);
        redirect(this,
                UserProposalListActivity.class,
                AnnouncementUtils.sendExtras(this.announcement));
    }

    public void submitProposal(View view) {
        this.isUserAuth(this);
        redirect(this,
                ProposalSubmitActivity.class,
                AnnouncementUtils.sendExtras(this.announcement));
    }

    public void removeAnnouncement(View v) {
        this.isUserAuth(this);
        if (this.announcement.getId() != null) {
            AnnouncementController.remove(this.announcement, new IResult<Announcement>() {
                @Override
                public void onSuccess(List<Announcement> list) {

                }

                @Override
                public void onSuccess(Announcement obj) {
                    setResult(Activity.RESULT_OK);
                    finish();
                }

                @Override
                public void onError(String msg) {
                    showToastMessage(
                            AnnouncementDetailsActivity.this, msg
                    );
                }
            });
        }
    }

}
