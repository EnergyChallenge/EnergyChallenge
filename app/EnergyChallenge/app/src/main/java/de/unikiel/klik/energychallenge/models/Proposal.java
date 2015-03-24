package de.unikiel.klik.energychallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Proposal implements Parcelable {

    private int id;

    private String description;

    private String author;

    private float rating;

    private List<ProposalComment> comments;

    public Proposal(int id, String description, String author, float rating, List<ProposalComment> comments) {

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

    public float getRating() {
        return rating;
    }

    public List<ProposalComment> getComments() {
        return comments;
    }


    @Override
    public int describeContents() {
        return 0; //Not necessary, but required
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(description);
        dest.writeString(author);
        dest.writeFloat(rating);
        dest.writeTypedList(comments);
    }

    public static final Parcelable.Creator<Proposal> CREATOR = new Parcelable.Creator<Proposal>() {
        public Proposal createFromParcel(Parcel in) {
            return new Proposal(in);
        }

        public Proposal[] newArray(int size) {
            return new Proposal[size];
        }
    };

    private Proposal(Parcel in) {
        id = in.readInt();
        description = in.readString();
        author = in.readString();
        rating = in.readInt();
        comments = in.createTypedArrayList(ProposalComment.CREATOR);
        //in.readTypedList(comments, ProposalComment.CREATOR);
        //comments = in.readParcelableArray(ProposalComment.class);
    }
}
