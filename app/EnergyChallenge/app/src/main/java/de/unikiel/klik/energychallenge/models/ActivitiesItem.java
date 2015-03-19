package de.unikiel.klik.energychallenge.models;

public class ActivitiesItem {

    private int id;

    private String description;

    public ActivitiesItem(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
