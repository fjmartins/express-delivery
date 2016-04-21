package controllers;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by kleber on 21/04/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this);
    }
}
