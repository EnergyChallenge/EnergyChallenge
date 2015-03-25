package de.unikiel.klik.energychallenge.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import de.unikiel.klik.energychallenge.tasks.GetMessagesTask;

public class NotificationReceiver extends BroadcastReceiver {
    public NotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //If there is at least one message, create a notification for one
        GetMessagesTask.checkForNotifications(context);
    }
}
