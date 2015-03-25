package de.unikiel.klik.energychallenge.models;

import java.util.List;

//TODO Merge User/Team to Profile and extend it

public class Team {

    private String name;

    private int points;

    private int position;

    private List<String> members;

    private List<String> lastActivities;

    public Team(String name, int points, int position,
                List<String> lastActivities) {
        this.name = name;
        this.points = points;
        this.position = position;
        this.lastActivities = lastActivities;
    }

    public Team(String name, int points, int position,
                List<String> lastActivities, List<String> members) {
        this.name = name;
        this.points = points;
        this.position = position;
        this.lastActivities = lastActivities;
        this.members = members;
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

    public List<String> getMembers() {
        return members;
    }

    public List<String> getLastActivities() {
        return lastActivities;
    }
}
