package de.unikiel.klik.energychallenge.models;

public class ProposalComment {

    private int id;

    private String author;

    private int rating;

    private String text;

    public ProposalComment(int id, String author, int rating, String text) {
        this.id = id;
        this.author = author;
        this.rating = rating;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public int getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }
}
