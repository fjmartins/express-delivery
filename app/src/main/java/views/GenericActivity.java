package views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by kleber on 20/04/16.
 */
public class GenericActivity extends AppCompatActivity {

    public void redirect(Activity context, Class to) {
        Intent i = new Intent(context, to);
        startActivity(i);
    }

    public void redirect(Activity context, Class to, Bundle extras) {
        Intent i = new Intent(context, to);
        i.putExtras(extras);
        startActivity(i);
    }

    public void showToastMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
