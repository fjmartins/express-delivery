package webservices;

import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import services.IResult;
import models.User;
import services.IResultUser;
import services.IUserAuth;

/**
 * Created by kleber on 25/04/16.
 */
public class UserAuthParse implements IUserAuth {
    @Override
    public void logIn(final User user, final IResultUser<User> result) {
        ParseUser parseUser = new ParseUser();

        parseUser.logInInBackground(user.getUsername(), user.getPassword(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUserInner, ParseException e) {
                if (e != null) {
                    result.onError(e.getMessage());
                }
                else if (parseUserInner != null) {
                    result.onSuccess(user);
                }
            }
        });
    }

    @Override
    public void logOut(final User user, final IResultUser<User> result) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.logOutInBackground(new LogOutCallback() {
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
    public void getCurrentUser(IResultUser<User> result) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
            result.onError("Nenhum usuário logado");
        } else {
            User user = new User();
            user.setUsername(currentUser.getUsername());
            result.onSuccess(user);
        }
    }
}
