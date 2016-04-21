package models;

import com.parse.ParseUser;

/**
 * Created by morte on 16/04/2016.
 */
public class User extends ParseUser {

    public User(String userName, String email, String password) {
        this.setUsername(userName);
        this.setEmail(email);
        this.setPassword(password);
    }
}
