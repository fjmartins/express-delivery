package controllers;

import models.User;
import services.IResultUser;
import services.IUserAuth;
import webservices.UserAuthParse;

/**
 * Created by kleber on 25/04/16.
 */
public class UserAuthController {

    public static void logIn(final User user, final IResultUser result) {
        IUserAuth userAuth = new UserAuthParse();
        userAuth.logIn(user, new IResultUser<User>() {
            @Override
            public void onSuccess(User obj) {
                result.onSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }

}
