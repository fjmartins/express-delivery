package dao;

import models.User;

/**
 * Created by kleber on 21/04/16.
 */
public interface IUserDao {
    
    void insert(User user, IResult<User> result);
    void update(User user, IResult<User> result);
    void remove(User user, IResult<User> result);

}
