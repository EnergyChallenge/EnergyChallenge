package de.unikiel.klik.energychallenge.services;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

import de.unikiel.klik.energychallenge.R;

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
        //TODO Change this to be every 12 hours after testing for final deployment
        Intent notificationIntent = new Intent();
        notificationIntent.setAction("de.unikiel.klik.energychallenge.NotificationReceiver");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pendingIntent);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingIntent);
    }

    public static void issueNotification(Context context){

        //Notification builder
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Energy Challenge")
                        .setContentText("Sie haben seit einiger Zeit keine Aktivitäten mehr durchgeführt!");

        //Build and issue Notification
        int notificationId = 007;
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());

        //Play the default notification sound if any exists on the device
        Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    mp.release();
                }
            });
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
