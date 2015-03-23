package de.unikiel.klik.energychallenge.tasks;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VerificationTask{

    /* Checks the persistent credentials are valid and returns the result as boolean */
    public static boolean checkCredentials(final String email, final String password){

        //Initalize the success variable
        final Boolean [] verificationSuccessful = new Boolean[1];
        verificationSuccessful[0] = false;

        /* Seperate thread to attempt a login with the credentials provided*/
        Thread loginAttempt = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //Create a new http client from the default
                    DefaultHttpClient client = new DefaultHttpClient();

                    //Set the parameters, post them to the server and collect a response
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("email", email));
                    params.add(new BasicNameValuePair("password", password));
                    //TODO use the actual server url for the post
                    HttpPost post = new HttpPost("http://192.168.0.2:8080/Server/auth/appLogin");
                    UrlEncodedFormEntity encodedEntity = new UrlEncodedFormEntity(params, "utf-8");
                    post.setEntity(encodedEntity);
                    HttpResponse httpResponse = client.execute(post);
                    HttpEntity responseEntity = httpResponse.getEntity();
                    InputStream inputStream = responseEntity.getContent();

                    //Convert the response to a useful string
                    String responseString = convertInputStreamToString(inputStream);

                    Log.v("Response: ", responseString);

                    JSONObject jsonResponse = new JSONObject(responseString);
                    String response = jsonResponse.getString("response");

                    Log.v("Response: ", response);

                    //Check the response for the result
                    if(response.toLowerCase().contains("true".toLowerCase())){
                        //Verification successful
                        verificationSuccessful[0] = true;
                    }else{

                        //Verification unsuccessful
                        verificationSuccessful[0] = false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        //Run the login thread
        loginAttempt.start();
        try {
            loginAttempt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return verificationSuccessful[0];
    }

    /* Input stream to string converter */
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;

    }
}
