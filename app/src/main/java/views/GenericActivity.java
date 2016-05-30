package views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.anderson.expressdelivery.R;

import controllers.UserAuthController;
import models.User;
import services.IResult;
import services.IResultUser;

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
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void isUserAuth(final Context context) {
        UserAuthController.getCurrentUser(new IResultUser<User>() {
            @Override
            public void onSuccess(User obj) {
                if (obj == null) {
                    redirect(GenericActivity.this, UserLoginActivity.class);
                }
            }

            @Override
            public void onError(String msg) {
                showToastMessage(context, msg);
                redirect(GenericActivity.this, UserLoginActivity.class);
            }
        });
    }

    public void goToMainIfUserAuth() {
        if (!getUsername().equals("")){
            redirect(GenericActivity.this, MainActivity.class);
        }
    }

    public String getUsername(){
        User user = UserAuthController.getCurrentUser();
        return (user == null) ? "" : user.getUsername();
    }

    public User getUser(){
        return UserAuthController.getCurrentUser();
    }

    @Override
    public void finish(){
        super.finish();

        overridePendingTransition(R.anim.main_activity_enter, R.anim.register_activity_exit);
    }

}
