package de.unikiel.klik.energychallenge.activities;

import android.app.Activity;
import android.os.Bundle;

import de.unikiel.klik.energychallenge.R;


/*The initial login activity which is bypassed if the user is already logged in*/
public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Load the state and login layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
