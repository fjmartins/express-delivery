package dao;

import models.User;
import services.IResult;
import services.IResultUser;

/**
 * Created by kleber on 21/04/16.
 */
public interface IUserDao {
    
    void signUp(User user, IResultUser<User> result);
    void update(User user, IResultUser<User> result);
    void remove(User user, IResultUser<User> result );

}
