package models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by morte on 16/04/2016.
 */
public class Announcement implements Serializable {

    private String title;
    private String description;
    private String endereco;
    private String telefone;
    private Bitmap picture;
    private String user;

    public Announcement(String user, String title, String description, String endereco, String telefone, Bitmap picture) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.endereco = endereco;
        this.telefone = telefone;
        this.picture = picture;
    }

    public Announcement(String title, String description, String telefone) {
        this.title = title;
        this.description = description;
        this.telefone = telefone;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
}
