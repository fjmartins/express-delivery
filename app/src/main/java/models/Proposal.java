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
    private User userFrom;
    private boolean isAccept;

    public Proposal() { }

    public Proposal(String title, String description, Double value){
        this.title = title;
        this.description = description;
        this.value = value;
    }
    public Proposal withTitle(String title) {
        this.title = title;
        return this;
    }

    public Proposal withDescription(String description) {
        this.description = description;
        return this;
    }

    public Proposal withValue(double value) {
        this.value = value;
        return this;
    }

    public Proposal withId(String id) {
        this.id = id;
        return this;
    }

    public Proposal withUserFrom(User user) {
        this.userFrom = user;
        return this;
    }

    public Proposal withAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
        return this;
    }

    public Proposal withIsAccept(boolean isAccept) {
        this.isAccept = isAccept;
        return this;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public User getUserFrom() {
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
}
