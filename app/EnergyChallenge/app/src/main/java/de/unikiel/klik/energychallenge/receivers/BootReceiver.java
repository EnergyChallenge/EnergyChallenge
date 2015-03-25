package de.unikiel.klik.energychallenge.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import de.unikiel.klik.energychallenge.services.NotificationService;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent intent) {

        //Start the notification service when the device boots
        Intent myIntent = new Intent(context, NotificationService.class);
        context.startService(myIntent);
    }
}
