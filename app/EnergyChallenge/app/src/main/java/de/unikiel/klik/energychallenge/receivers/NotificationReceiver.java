package de.unikiel.klik.energychallenge.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import de.unikiel.klik.energychallenge.tasks.GetMessagesTask;
import de.unikiel.klik.energychallenge.utils.NetworkX;

public class NotificationReceiver extends BroadcastReceiver {
    public NotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //Get the messages and if there is at least on create a notification
        if (NetworkX.isAvailable(context)) {

            //There is a network connection, invoke the messages task
            new GetMessagesTask(context).execute();
        } else {
            //Do nothing as there is no network connection
        }
    }
}
