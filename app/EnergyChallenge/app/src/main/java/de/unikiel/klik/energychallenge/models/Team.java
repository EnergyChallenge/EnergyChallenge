package de.unikiel.klik.energychallenge.models;

import java.util.List;

public class Team extends Profile {

    private List<String> members;

    public Team(String name, int points, int position,
                List<String> lastActivities, List<String> members) {
        super(name, points, position, lastActivities);
        this.members = members;
    }

    public List<String> getMembers() {
        return members;
    }

}
