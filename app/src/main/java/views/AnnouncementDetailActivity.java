package views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;

import models.Announcement;

public class AnnouncementDetailActivity extends GenericActivity {

    private TextView title, description, phone, address;
    private ImageView picture;
    private Announcement announcement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_detail_activity);

        this.title = (TextView)findViewById(R.id.txt_announcement_detail_activity_title);
        this.description = (TextView)findViewById(R.id.txt_announcement_detail_activity_description);
        this.phone = (TextView)findViewById(R.id.txt_announcement_detail_activity_phone);
        this.address = (TextView)findViewById(R.id.txt_announcement_detail_activity_address);
        this.picture = (ImageView)findViewById(R.id.img_announcement_detail_activity_picture);

        loadData();
    }

    public void atualizar(View v) {
        Bundle extras = new Bundle();
        extras.putSerializable("announcement", this.announcement);
        redirect(this, AnnouncementRegisterActivity.class, extras);
    }

    private void loadData() {
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            this.announcement = (Announcement)extras.get("announcement");

            this.title.setText(announcement.getTitle());
            this.description.setText(announcement.getDescription());
            this.phone.setText(announcement.getTelefone());
            this.address.setText(announcement.getEndereco());
            this.picture.setImageBitmap(announcement.getPicture());
        }
    }
}
