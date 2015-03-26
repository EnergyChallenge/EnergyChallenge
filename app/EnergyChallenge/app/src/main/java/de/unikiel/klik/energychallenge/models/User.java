package de.unikiel.klik.energychallenge.models;

import java.util.List;

public class User extends Profile {

    private String teamName;

    private String institute;

    public User(String name, String teamName, String institute, int points, int position, List<String> lastActivities) {
        super(name, points, position, lastActivities);
        this.teamName = teamName;
        this.institute = institute;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getInstitute() {
        return institute;
    }

}
