package de.unikiel.klik.energychallenge.models;

import java.util.List;

//TODO Merge User/Team to Profile and extend it

public class User {

    private int id;

    private String name;

    private String teamName;

    private String institute;

    private int points;

    private int position;

    private List<String> lastActivities;

    public User(int id, String name, String teamName, String institute, int points, int position, List<String> lastActivities) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.institute = institute;
        this.points = points;
        this.position = position;
        this.lastActivities = lastActivities;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getInstitute() {
        return institute;
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
