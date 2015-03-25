package de.unikiel.klik.energychallenge.tasks;

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

import de.unikiel.klik.energychallenge.utils.IoX;

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
                    String responseString = IoX.readInputStream(inputStream, inputStream.toString().length());

                    //Check the response for the result
                    if(responseString.contains("true")){

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
}
