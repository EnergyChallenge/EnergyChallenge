package de.unikiel.klik.energychallenge.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.Calendar;

public class NotificationService extends Service {

    public NotificationService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        //Set the alarm when the service starts
        setNotificationAlarm();

        return Service.START_NOT_STICKY;
    }

    public void setNotificationAlarm(){

        //Broadcast to the notification receiver should be issued and do so again every 15 minutes
        //TODO Change this to be every 12 hours after for deployment
        Intent notificationIntent = new Intent();
        notificationIntent.setAction("de.unikiel.klik.energychallenge.NotificationReceiver");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pendingIntent);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
