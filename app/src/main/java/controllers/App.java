package controllers;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

/**
 * Created by kleber on 21/04/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this);
        ParseFacebookUtils.initialize(this, 1000);
    }
}
