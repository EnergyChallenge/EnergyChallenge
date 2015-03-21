package de.unikiel.klik.energychallenge.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.activities.LoginActivity;

/* Fragment for the options menu */
public class OptionsFragment extends PreferenceFragment {

    private Context applicationContext;

    private SharedPreferences preferences;

    public OptionsFragment() {
    }

    /* Returns an new instance of this fragment */
    public static OptionsFragment newInstance() {
        return new OptionsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        applicationContext = getActivity().getApplicationContext();

        preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);

        addPreferencesFromResource(R.xml.preferences);

        findPreference("email").setSummary(preferences.getString("email", ""));
        findPreference("name").setSummary(preferences.getString("name", ""));

        findPreference("logout").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent);

                return true;
            }
        });

    }

}