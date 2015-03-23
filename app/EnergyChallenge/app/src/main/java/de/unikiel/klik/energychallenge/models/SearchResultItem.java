package de.unikiel.klik.energychallenge.models;

public class SearchResultItem {

    private int id;

    private String name;

    private String type;

    public SearchResultItem(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
