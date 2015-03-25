package de.unikiel.klik.energychallenge.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

import de.unikiel.klik.energychallenge.Config;

public class DownloadAvatarTask extends AsyncTask<String, Void, Bitmap> {

    private int profileId;

    private ImageView avatarView;

    public DownloadAvatarTask(int profileId, ImageView avatarView) {
        this.profileId = profileId;
        this.avatarView = avatarView;
    }

    @Override
    protected final Bitmap doInBackground(String... params) {
        //TODO Hardcoded URL
        String avatarUrl = "http://192.168.0.83:8080/Server/" + "profile/avatar/" + Integer.toString(profileId);
        Bitmap avatar = null;
        try {
            InputStream in = new java.net.URL(avatarUrl).openStream();
            avatar = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            //TODO Handle Error
            e.printStackTrace();
        }
        return avatar;
    }

    @Override
    protected final void onPostExecute(Bitmap result) {
        avatarView.setImageBitmap(result);
    }

}