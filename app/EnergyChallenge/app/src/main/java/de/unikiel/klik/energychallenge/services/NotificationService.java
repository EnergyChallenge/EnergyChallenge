package de.unikiel.klik.energychallenge.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import de.unikiel.klik.energychallenge.R;

public class NotificationService extends Service {
    public NotificationService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        /* Test notification */
        //Notification builder
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Energy Challenge")
                        .setContentText("Good Day! :D");

        //Build and issue Notification
        int notificationId = 007;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
