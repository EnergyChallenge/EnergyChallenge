package de.unikiel.klik.energychallenge.tasks;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import de.unikiel.klik.energychallenge.services.NotificationService;
import de.unikiel.klik.energychallenge.utils.ServerRequest;

public class GetMessagesTask extends AccessServerTask{

    Context notificationContext;

    public GetMessagesTask(Context context){
        super(context);
        notificationContext = context;
    }

    @Override
    protected ServerRequest createServerRequest() {
        return new ServerRequest("messages");
    }

    @Override
    protected void handleServerResponse(JSONObject response) throws JSONException {

        JSONObject messages = response.getJSONObject("messages");

        if(messages.toString().contains("de.unikiel.klik.persistence.ActivityNotification")) {

            //There is a reminder tell the notifications service to issue a notification
            NotificationService.issueNotification(notificationContext);
        }

    }

    @Override
    protected void handleResponseError() {

    }
}