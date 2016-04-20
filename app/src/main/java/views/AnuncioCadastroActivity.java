package views;

import android.app.Activity;
import android.os.Bundle;

import com.example.anderson.expressdelivery.R;

/**
 * Created by Allan-PC on 17/04/2016.
 */
public class AnuncioCadastroActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.anuncio_cadastro_activity);
    }
}
