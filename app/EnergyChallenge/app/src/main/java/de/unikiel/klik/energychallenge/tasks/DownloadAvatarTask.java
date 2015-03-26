package de.unikiel.klik.energychallenge.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

import de.unikiel.klik.energychallenge.Config;

public class DownloadAvatarTask extends AsyncTask<String, Void, Bitmap> {

    private String TAG = "DownloadAvatarTask";

    private int profileId;

    private ImageView avatarView;

    public DownloadAvatarTask(int profileId, ImageView avatarView) {
        this.profileId = profileId;
        this.avatarView = avatarView;
    }

    @Override
    protected final Bitmap doInBackground(String... params) {
        String avatarUrl = Config.SERVER_REST_PATH + "avatar/" + Integer.toString(profileId);
        Bitmap avatar = null;
        try {
            InputStream in = new java.net.URL(avatarUrl).openStream();
            avatar = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e(TAG, "Couldn't get image from server.");
            e.printStackTrace();
        }
        return avatar;
    }

    @Override
    protected final void onPostExecute(Bitmap result) {
        avatarView.setImageBitmap(result);
    }

}