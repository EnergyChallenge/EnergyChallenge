package de.unikiel.klik.energychallenge.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.fragments.MyProfileFragment;

//TODO Maybe Add Searchbar to Profile

public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //TODO TEMP
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        int id = intent.getIntExtra("id", 0);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MyProfileFragment())
                    .commit();
        }
    }

}