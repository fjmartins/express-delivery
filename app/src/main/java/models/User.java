package models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by morte on 16/04/2016.
 */
public class User implements Serializable{

    private String username;
    private String email;
    private String password;
    private List<Address> addresses;

    public User(String username, String email, String password) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public User() { }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
