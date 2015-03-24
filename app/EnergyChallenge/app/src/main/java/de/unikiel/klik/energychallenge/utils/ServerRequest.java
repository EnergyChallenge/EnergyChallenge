package de.unikiel.klik.energychallenge.utils;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class ServerRequest {

    private String receiverOnServer;

    private Integer id;

    private ArrayList<BasicNameValuePair> parameters;

    private JSONObject requestData;


    public ServerRequest(String receiverOnServer) {
        this(receiverOnServer, new JSONObject());
    }

    public ServerRequest(String receiverOnServer, int id) {
        this.receiverOnServer = receiverOnServer;
        this.id = id;
    }

    public ServerRequest(String receiverOnServer, ArrayList<BasicNameValuePair> parameters) {
        this.receiverOnServer = receiverOnServer;
        this.parameters = parameters;
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

    public boolean isIdSet() {
        return (id != null);
    }

    public int getId() {
        return id.intValue();
    }

    public void setId(int id) {
        this.id = new Integer(id);
    }

    public ArrayList<BasicNameValuePair> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<BasicNameValuePair> parameters) {
        this.parameters = parameters;
    }

    public JSONObject getRequestData() {
        return requestData;
    }

    public void setRequestData(JSONObject requestData) {
        this.requestData = requestData;
    }

}
