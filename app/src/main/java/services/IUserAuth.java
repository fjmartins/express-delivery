package services;

import models.User;

/**
 * Created by kleber on 25/04/16.
 */
public interface IUserAuth {

    void logIn(User user, IResultUser<User> result);
    void logOut(User user, IResultUser<User> result);
    void getCurrentUser(IResultUser<User> result);
}
