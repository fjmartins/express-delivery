package controllers;

import java.util.List;

import services.IResult;
import dao.IUserDao;
import models.User;
import services.IResultUser;
import webservices.UserParse;

/**
 * Created by kleber on 25/04/16.
 */
public class UserController {

    public static void signUp(final User user, final IResultUser<User> result) {
        IUserDao userDao = new UserParse();
        userDao.signUp(user, new IResultUser<User>() {
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
