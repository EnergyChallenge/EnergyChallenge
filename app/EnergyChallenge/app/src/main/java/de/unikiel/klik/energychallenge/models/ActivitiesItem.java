package de.unikiel.klik.energychallenge.models;

public class ActivitiesItem {

    private int id;

    private String description;

    private boolean active;

    public ActivitiesItem(int id, String description, boolean active) {
        this.id = id;
        this.description = description;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
