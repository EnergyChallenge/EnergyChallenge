package de.unikiel.klik.energychallenge.utils;

import org.json.JSONObject;

public class ServerRequest {

    private String receiverOnServer;

    private JSONObject requestData;

    public ServerRequest(String receiverOnServer) {
        this(receiverOnServer, new JSONObject());
    }

    public ServerRequest(String receiverOnServer, JSONObject requestData) {
        this.receiverOnServer = receiverOnServer;
        this.requestData = requestData;
    }

    public String getReceiverOnServer() {
        return receiverOnServer;
    }

    public void setReceiverOnServer(String receiverOnServer) {
        this.receiverOnServer = receiverOnServer;
    }

    public JSONObject getRequestData() {
        return requestData;
    }

    public void setRequestData(JSONObject requestData) {
        this.requestData = requestData;
    }

}
