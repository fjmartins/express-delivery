package views;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderson.expressdelivery.R;

/**
 * Created by anderson on 10/05/16.
 */
public class MyAnnoucementActivity extends GenericActivity {
    private TextView titulo;
    private TextView currentDate;
    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_annoucement_activity);

        this.isUserAuth(this);

        this.picture = (ImageView) findViewById(R.id.img_announcement_activity);
        this.titulo = (TextView) findViewById(R.id.txtTituloMeusAnuncios);
        this.currentDate = (TextView) findViewById(R.id.txtDate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.isUserAuth(this);
    }

}
