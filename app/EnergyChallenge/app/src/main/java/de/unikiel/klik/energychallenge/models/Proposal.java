package de.unikiel.klik.energychallenge.models;

import java.util.ArrayList;

public class Proposal {

    private int id;

    private String description;

    private String author;

    private int rating;

    private ArrayList<ProposalComment> comments;

    public Proposal(int id, String description, String author, int rating, ArrayList<ProposalComment> comments) {

        this.id = id;
        this.description = description;
        this.author = author;
        this.rating = rating;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public int getRating() {
        return rating;
    }

    public ArrayList<ProposalComment> getComments() {
        return comments;
    }


}
