package models;

/**
 * Created by kleber on 10/05/16.
 */
public class Proposal {

    private String id, title, description;
    private double value;

    public Proposal() { }

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
