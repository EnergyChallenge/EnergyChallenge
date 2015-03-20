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

        //Pass parameters from Intent to Fragment
        Intent intent = getIntent();
        Bundle fragmentArguments = new Bundle();
        fragmentArguments.putString("type", intent.getStringExtra("type"));
        fragmentArguments.putInt("id", intent.getIntExtra("id", 0));

        MyProfileFragment profileFragment = new MyProfileFragment();
        profileFragment.setArguments(fragmentArguments);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, profileFragment)
                    .commit();
        }
    }

}