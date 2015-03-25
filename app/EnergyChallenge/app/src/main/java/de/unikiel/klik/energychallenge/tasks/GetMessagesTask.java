package de.unikiel.klik.energychallenge.tasks;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.utils.IoX;

public class GetMessagesTask{

    public static boolean checkForNotifications(Context context){

        final Context contextForThread = context;
        //Get the email and password from the shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String persistentEmail = preferences.getString("email", "");
        final String persistentPassword = preferences.getString("password", "");

        /* Seperate thread to get the messages and possibly create a notification*/
        Thread createNotification = new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    //Create a new http client from the default
                    DefaultHttpClient client = new DefaultHttpClient();

                    //Set the parameters, post them to the server and collect a response
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("email", persistentEmail));
                    params.add(new BasicNameValuePair("password", persistentPassword));
                    //TODO use the actual server url for the post
                    HttpPost post = new HttpPost("http://192.168.0.2:8080/Server/app/messages");
                    UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "utf-8");
                    post.setEntity(encodedEntity);
                    HttpResponse httpResponse = client.execute(post);
                    HttpEntity responseEntity = httpResponse.getEntity();
                    InputStream inputStream = responseEntity.getContent();

                    //Convert the response to a useful string
                    String responseString = IoX.readInputStream(inputStream, inputStream.toString().length());
                    Log.v("Response from server", responseString);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //Run the notification thread
        createNotification.start();

        /* Test notification */
        //Notification builder
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(contextForThread)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Energy Challenge")
                        .setContentText("Good Day");

        //Build and issue Notification
        int notificationId = 007;
        NotificationManager notificationManager = (NotificationManager)contextForThread.getSystemService(contextForThread.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());

        return true;
    }

}
