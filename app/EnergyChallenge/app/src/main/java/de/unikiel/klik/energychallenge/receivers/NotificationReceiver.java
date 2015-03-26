package de.unikiel.klik.energychallenge.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import de.unikiel.klik.energychallenge.tasks.GetMessagesTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;

public class NotificationReceiver extends BroadcastReceiver {
    public NotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //See if the user has notifications turned on
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean notifications = sharedPreferences.getBoolean("notifications", true);

        //Get the messages and if there is at least one create a notification
        if (NetworkX.isAvailable(context) && notifications) {

            //There is a network connection and the user has notifications turned on
            //invoke the messages task
            new GetMessagesTask(context).execute();
        } else {
            //Do nothing as there is no network connection
        }
    }
}
