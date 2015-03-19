package de.unikiel.klik.energychallenge.models;

public class RankingItem {

    private int position;

    private int id;

    private String title;

    private int points;

    public RankingItem(int position, int id, String title, int points) {
        this.position = position;
        this.id = id;
        this.title = title;
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPoints() {
        return points;
    }
}
