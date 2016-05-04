package models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by morte on 16/04/2016.
 */
public class Announcement implements Serializable {

    private String title;
    private String description;
    private String address;
    private String phone;
    private Bitmap picture;
    private String user;
    private String id;

    public Announcement(String user, String title, String description, String address, String phone, Bitmap picture) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
    }

    public Announcement(String title, String description, String phone) {
        this.title = title;
        this.description = description;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
