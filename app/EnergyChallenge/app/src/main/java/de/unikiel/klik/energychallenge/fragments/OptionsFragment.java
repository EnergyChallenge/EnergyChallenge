package de.unikiel.klik.energychallenge.fragments;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

import de.unikiel.klik.energychallenge.R;
import de.unikiel.klik.energychallenge.activities.LoginActivity;
import de.unikiel.klik.energychallenge.utils.CurrentUser;

/* Fragment for the options menu */
public class OptionsFragment extends PreferenceFragment {

    private static final String  TAG = "OptionsFragment";

    public OptionsFragment() {
    }

    /* Returns an new instance of this fragment */
    public static OptionsFragment newInstance() {
        return new OptionsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final CurrentUser currentUser = new CurrentUser(getActivity().getApplicationContext());

        addPreferencesFromResource(R.xml.preferences);

        findPreference("email").setSummary(currentUser.getEmail());
        findPreference("name").setSummary(currentUser.getName());

        try {
            String packageName = getActivity().getPackageName();
            PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(packageName, 0);
            String buildVersion = packageInfo.versionName;
            findPreference("version").setSummary(buildVersion);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Couldn't get version name from manifest");
            e.printStackTrace();
        }

        findPreference("logout").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                currentUser.clear();

                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent);

                return true;
            }
        });

    }

}