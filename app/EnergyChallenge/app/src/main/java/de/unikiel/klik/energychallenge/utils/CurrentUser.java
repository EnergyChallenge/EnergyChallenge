package de.unikiel.klik.energychallenge.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CurrentUser {

    private Context applicationContext;

    private SharedPreferences preferences;

    public CurrentUser(Context applicationContext) {
        this.applicationContext = applicationContext;
        preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    public String getEmail() {
        return preferences.getString("email", "");
    }

    public String getPassword() {
        return preferences.getString("password", "");
    }

    public String getName() {
        return preferences.getString("name", "");
    }

    public void setEmail(String email) {
        preferences.edit().putString("email", email).commit();
    }

    public void setPassword(String password) {
        preferences.edit().putString("password", password).commit();
    }
    public void setName(String name) {
        preferences.edit().putString("name", name).commit();
    }

}
