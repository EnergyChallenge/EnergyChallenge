package de.unikiel.klik.energychallenge.models;

import java.util.ArrayList;

public class Proposal {

    private int id;

    private String description;

    private int rating;

    private ArrayList<ProposalComment> comments;

    public Proposal(int id, String description, int rating, ArrayList<ProposalComment> comments) {

        this.id = id;
        this.description = description;
        this.rating = rating;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public ArrayList<ProposalComment> getComments() {
        return comments;
    }

}
