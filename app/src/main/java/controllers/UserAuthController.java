package controllers;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import models.User;
import services.IResult;
import services.IResultUser;
import services.IUserAuth;
import webservices.UserAuthParse;

/**
 * Created by kleber on 25/04/16.
 */
public class UserAuthController {

    private static IUserAuth userAuth = new UserAuthParse();

    public static void logIn(final User user, final IResultUser<User> result) {
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

    public static void logOut(final User user, final IResultUser<User> result) {
        userAuth.logOut(user, new IResultUser<User>() {
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

    public static void getCurrentUser(final IResultUser<User> result) {
        userAuth.getCurrentUser(new IResultUser<User>() {
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

    public static User getCurrentUser() {
        return userAuth.getCurrentUser();
    }

    public static boolean resetPassword(String email) {
        try {
            ParseUser.requestPasswordReset(email);
            return true;
        } catch(ParseException e) {
            return false;
        }
    }
}
