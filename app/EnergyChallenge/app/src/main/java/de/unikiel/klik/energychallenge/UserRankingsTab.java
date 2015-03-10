package de.unikiel.klik.energychallenge;


import android.app.Activity;
import android.os.Bundle;


/* Fragment for the initial view upon login */
public class UserRankingsTab extends Activity {

    public UserRankingsTab(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_user_rankings);
    }
}