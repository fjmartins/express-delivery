package views;

import android.graphics.Bitmap;
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

        this.title = (TextView) findViewById(R.id.txt_announcement_detail_activity_title);
        this.description = (TextView) findViewById(R.id.txt_announcement_detail_activity_description);
        this.phone = (TextView) findViewById(R.id.txt_announcement_detail_activity_phone);
        this.address = (TextView) findViewById(R.id.txt_announcement_detail_activity_address);
        this.picture = (ImageView) findViewById(R.id.img_announcement_detail_activity_picture);

        loadData();
    }

    public void update(View v) {
        this.isUserAuth(this);
        Bundle extras = new Bundle();
        extras.putString("description", announcement.getDescription());
        extras.putString("address", announcement.getAddress());
        extras.putString("phone", announcement.getPhone());
        extras.putString("tittle", announcement.getTitle());
        extras.putParcelable("picture", announcement.getPicture());
        redirect(this, AnnouncementRegisterActivity.class, extras);
    }

    private void loadData() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("picture");
            Announcement announcement = new Announcement(null, extras.get("tittle").toString(),
                    extras.get("description").toString(), extras.get("address").toString(),
                    extras.get("phone").toString(), bitmap);

            this.announcement = announcement;

            this.title.setText(announcement.getTitle());
            this.description.setText(announcement.getDescription());
            this.phone.setText(announcement.getPhone());
            this.address.setText(announcement.getAddress());
            this.picture.setImageBitmap(announcement.getPicture());
        }
    }
}
