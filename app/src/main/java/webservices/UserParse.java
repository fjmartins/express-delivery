package webservices;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import services.IResult;
import dao.IUserDao;
import models.User;
import services.IResultUser;

/**
 * Created by kleber on 25/04/16.
 */
public class UserParse implements IUserDao {
    @Override
    public void signUp(final User user, final IResultUser<User> result) {
        ParseUser parseUser = new ParseUser();

        parseUser.setEmail(user.getEmail());
        parseUser.setUsername(user.getUsername());
        parseUser.setPassword(user.getPassword());

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    result.onSuccess(user);
                } else {
                    result.onError(e.getMessage());
                }
            }
        });
    }

    @Override
    public void update(User user, IResultUser<User> result) {

    }

    @Override
    public void remove(User user, IResultUser<User> result) {

    }
}
