package views;

import android.graphics.Bitmap;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.getbase.floatingactionbutton.FloatingActionButton;

import com.example.anderson.expressdelivery.R;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import controllers.AnnouncementController;
import controllers.ProposalController;
import models.Announcement;
import models.Proposal;
import services.IResult;

public class AnnouncementDetailActivity extends GenericActivity {

    private TextView title, description, phone, address;
    private ImageView picture;
    private FloatingActionButton btnDetail;
    private FloatingActionButton btnSubmitProposal;
    private Button btnShowProposal;
    private Announcement announcement;
    private Button btnDelete;

    private FloatingActionsMenu btnmultiple_actions;
    private FloatingActionButton btnfbListProposal;
    private FloatingActionButton btnfbEditDetAnunc;
    private FloatingActionButton btnfbdelete_announcment;
    private FloatingActionButton btnfbProposalSubmit;

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
        //this.btnDetail = (FloatingActionButton) findViewById(R.id.fabEditDetAnunc);
        //this.btnDelete = (Button) findViewById(R.id.btn_delete_announcment);
        //this.btnSubmitProposal = (FloatingActionButton) findViewById(R.id.fabProposalSubmit);
        //this.btnShowProposal = (Button) findViewById(R.id.btn_show_proposal);

        this.btnmultiple_actions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        this.btnfbListProposal = (FloatingActionButton) findViewById(R.id.fbListProposal);
        this.btnfbEditDetAnunc = (FloatingActionButton) findViewById(R.id.fbEditDetAnunc);
        this.btnfbdelete_announcment = (FloatingActionButton) findViewById(R.id.fbdelete_announcment);
        this.btnfbProposalSubmit = (FloatingActionButton) findViewById(R.id.fbProposalSubmit);

        loadData();
    }

    public void update(View v) {
        this.isUserAuth(this);
        Bundle extras = new Bundle();
        extras.putString("description", announcement.getDescription());
        extras.putString("address", announcement.getAddress());
        extras.putString("phone", announcement.getPhone());
        extras.putString("tittle", announcement.getTitle());
        extras.putString("id", announcement.getId());
        extras.putParcelable("picture", announcement.getPicture());
        redirect(this, AnnouncementRegisterActivity.class, extras);
    }

    public void showProposal(View view){
        this.isUserAuth(this);
        Bundle extras = new Bundle();
        extras.putString("description", announcement.getDescription());
        extras.putString("address", announcement.getAddress());
        extras.putString("phone", announcement.getPhone());
        extras.putString("tittle", announcement.getTitle());
        extras.putString("id", announcement.getId());
        extras.putParcelable("picture", announcement.getPicture());
        redirect(this, UserProposalListActivity.class, extras);
    }

    private void loadData() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("picture");

            Announcement announcement = new Announcement(null, extras.get("tittle").toString(),
                    extras.get("description").toString(), extras.get("address").toString(),
                    extras.get("phone").toString(), bitmap);
            announcement.setUser(extras.getString("username"));
            if (extras.get("id") != null)
                announcement.setId(extras.get("id").toString());

            this.announcement = announcement;

            this.title.setText(announcement.getTitle());
            this.description.setText(announcement.getDescription());
            this.phone.setText(announcement.getPhone());
            this.address.setText(announcement.getAddress());
            this.picture.setImageBitmap(announcement.getPicture());

            if(!this.getUsername().equals(announcement.getUser())){
                //this.btnDetail.setVisibility(View.INVISIBLE);
                //this.btnDelete.setVisibility(View.INVISIBLE);
                //this.btnShowProposal.setVisibility(View.INVISIBLE);
                this.btnfbEditDetAnunc.setVisibility(View.INVISIBLE);
                this.btnfbdelete_announcment.setVisibility(View.INVISIBLE);
                this.btnfbListProposal.setVisibility(View.INVISIBLE);
                this.btnmultiple_actions.setVisibility(View.INVISIBLE);

            }else{
                //this.btnSubmitProposal.setVisibility(View.INVISIBLE);
                this.btnfbProposalSubmit.setVisibility(View.INVISIBLE);
            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public void submitProposal(View view){
        Bundle extras = new Bundle();
        extras.putString("description", announcement.getDescription());
        extras.putString("address", announcement.getAddress());
        extras.putString("phone", announcement.getPhone());
        extras.putString("tittle", announcement.getTitle());
        extras.putString("id", announcement.getId());
        extras.putParcelable("picture", announcement.getPicture());
        redirect(this, ProposalSubmitActivity.class, extras);
    }

    public void remove(View v){
        this.isUserAuth(this);
        if (this.announcement.getId() != null) {
            AnnouncementController.remove(this.announcement, new IResult<Announcement>() {
                @Override
                public void onSuccess(List<Announcement> list) {

                }

                @Override
                public void onSuccess(Announcement obj) {
                    finish();
                }

                @Override
                public void onError(String msg) {
                    showToastMessage(
                            AnnouncementDetailActivity.this, msg
                    );
                }
            });
        }
    }



}
