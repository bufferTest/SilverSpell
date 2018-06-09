package bkh.com.silverspell.utils;

import android.content.Context;
import android.content.SharedPreferences;

import bkh.com.silverspell.R;
import bkh.com.silverspell.app;

public class AppPreference {
    private static final AppPreference ourInstance = new AppPreference();
    private SharedPreferences sharedpref;

    public static AppPreference getInstance() {
        return ourInstance;
    }

    private AppPreference() {
        Context context = app.getAppContext();
        sharedpref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void clearSharedPrefrences() {
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.clear().apply();
    }

    public void putInteger(String key, int value) {
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedpref.getString(key, "");
    }

    public int getInteger(String key) {
        return sharedpref.getInt(key, -1);
    }

    public boolean getBoolean(String key) {
        return sharedpref.getBoolean(key, false);
    }
}
