package views;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.anderson.expressdelivery.R;

/**
 * Created by Allan-PC on 22/05/2016.
 */
public class SplashActivity extends Activity {
    private final int DURAÇAO_DA_TELA = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent minhaAcao = new Intent(SplashActivity.this, UserLoginActivity.class);
                SplashActivity.this.startActivity(minhaAcao);
                SplashActivity.this.finish();
            }
        }, DURAÇAO_DA_TELA);
    }
}
