package de.unikiel.klik.energychallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProposalComment implements Parcelable  {

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

    @Override
    public int describeContents() {
        return 0; //Not necessary, but required
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(author);
        dest.writeInt(rating);
        dest.writeString(text);
    }

    public static final Parcelable.Creator<ProposalComment> CREATOR = new Parcelable.Creator<ProposalComment>() {
        public ProposalComment createFromParcel(Parcel in) {
            return new ProposalComment(in);
        }

        public ProposalComment[] newArray(int size) {
            return new ProposalComment[size];
        }
    };

    private ProposalComment(Parcel in) {
        id = in.readInt();
        author = in.readString();
        rating = in.readInt();
        text = in.readString();
    }
}
