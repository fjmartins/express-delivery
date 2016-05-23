package models;

/**
 * Created by kleber on 10/05/16.
 */
public class Proposal {

    private String id;
    private String title;
    private String description;
    private String announcementId;
    private double value;
    private String userFrom;
    private boolean isAccept;

    public Proposal() { }

    public Proposal(String title, String description, Double value){
        this.title = title;
        this.description = description;
        this.value = value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setAccept(boolean accept) {
        isAccept = accept;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }
}
