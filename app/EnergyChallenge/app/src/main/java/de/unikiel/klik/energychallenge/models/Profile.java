package de.unikiel.klik.energychallenge.models;

import java.util.List;

public abstract class Profile {

    private String name;

    private int points;

    private int position;

    private List<String> lastActivities;

    public Profile(String name, int points, int position, List<String> lastActivities) {
        this.name = name;
        this.points = points;
        this.position = position;
        this.lastActivities = lastActivities;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getPosition() {
        return position;
    }

    public List<String> getLastActivities() {
        return lastActivities;
    }

}
