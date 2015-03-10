package de.unikiel.klik.energychallenge;


import android.app.Activity;
import android.os.Bundle;


/* Fragment for the initial view upon login */
public class TeamRankingsTab extends Activity {

    public TeamRankingsTab(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_team_rankings);
    }
}