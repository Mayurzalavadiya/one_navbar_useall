package com.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.myapplication.ApplicationController;

import java.util.Set;

public class PrefsManager {

    private static final String SP_NAME = "PREFS_BASE_APP";

    public static final String FCM_TOKEN = "FCM_TOKEN";
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String STAFF_ID = "STAFF_ID";
    public static final String COMPANY_USER_ID = "COMPANY_USER_ID";
    public static final String COMPANY_HOST_USER_ID = "COMPANY_HOST_USER_ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String PASSWORD = "PASSWORD";
    public static final String HOST_FIRST_NAME = "HOST_FIRST_NAME";
    public static final String HOST_LAST_NAME = "HOST_LAST_NAME";


    public static final String LAST_NAME = "LAST_NAME";
    public static final String PROFILE_IMAGE = "PROFILE_IMAGE";
    public static final String EMAIL = "EMAIL";

    public static final String COMPANY_NAME = "COMPANY_NMAE";
    public static final String COMPANY_HOST_NAME = "COMPANY_NMAE";
    public static final String CITY = "CITY";

    public static final String DOCUMENT_Url = "DOCUMENT_URL";


    public static final String HOST_USER_ID = "CONTACT_USER_ID";


    public static final String VISITOR_PURPOSE_ID = "VISITOR_PURPOSE_ID";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SP_NAME, 0); // Context.MODE_PRIVATE);
    }

    /**
     * ---     String      ---
     **/
    public static void savePrefsVal(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(ApplicationController.getAppContext()).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String readStringPrefsVal(String key) {
        return getSharedPreferences(ApplicationController.getAppContext()).getString(key, null);
    }

    /**
     * ---     boolean      ---
     **/
    public static void savePrefsVal(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences(ApplicationController.getAppContext()).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean readBooleanPrefsVal(String key) {
        return getSharedPreferences(ApplicationController.getAppContext()).getBoolean(key, false);
    }

    /**
     * ---     float      ---
     **/
    public static void savePrefsVal(String key, float value) {
        SharedPreferences.Editor editor = getSharedPreferences(ApplicationController.getAppContext()).edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static float readFloatPrefsVal(String key) {
        return getSharedPreferences(ApplicationController.getAppContext()).getFloat(key, 0.0F);
    }

    /**
     * ---     int      ---
     **/
    public static void savePrefsVal(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(ApplicationController.getAppContext()).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int readIntPrefsVal(String key) {
        return getSharedPreferences(ApplicationController.getAppContext()).getInt(key, 0);
    }

    /**
     * ---     long      ---
     **/
    public static void savePrefsVal(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences(ApplicationController.getAppContext()).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long readLongPrefsVal(String key) {
        return getSharedPreferences(ApplicationController.getAppContext()).getLong(key, 0L);
    }

    /**
     * ---     String-Set      ---
     **/
    public static void savePrefsVal(String key, Set<String> stringSet) {
        SharedPreferences.Editor editor = getSharedPreferences(ApplicationController.getAppContext()).edit();
        editor.putStringSet(key, stringSet);
        editor.apply();
    }

    public static Set<String> readStringSetPrefsVal(String key) {
        return getSharedPreferences(ApplicationController.getAppContext()).getStringSet(key, null);
    }

    public static boolean containsKey(String key) {
        return getSharedPreferences(ApplicationController.getAppContext()).contains(key);
    }

    public static void removeKey(String key) {
        SharedPreferences.Editor editor = getSharedPreferences(ApplicationController.getAppContext()).edit();
        editor.remove(key);
        editor.apply();
    }

    public static void removeAllKey() {
        getSharedPreferences(ApplicationController.getAppContext()).edit().clear().apply();
    }

    /*public static void setUserInfo(UseInfo userinfo) {
        SharedPreferences.Editor editor = getSharedPreferences(ApplicationController.getAppContext()).edit();
        editor.putString(PREFS_USER_INFO, new Gson().toJson(userinfo));
        editor.apply();
    }

    public static UseInfo getUserInfo() {
        return new Gson().fromJson(getSharedPreferences(ApplicationController.getAppContext()).getString(PREFS_USER_INFO, null), UseInfo.class);
    }*/

    /*public static ArrayList<UserData> getRegisteredUserList() {
        Type type = new TypeToken<ArrayList<UserData>>() {
        }.getType();
        return new Gson().fromJson(PrefsManager.readStringPrefsVal(PrefsManager.REGISTERD_USER_LIST), type);
    }

    public static ArrayList<UserData> getUnregisteredUserList() {
        Type type = new TypeToken<ArrayList<UserData>>() {
        }.getType();
        return new Gson().fromJson(PrefsManager.readStringPrefsVal(PrefsManager.UN_REGISTERD_USER_LIST), type);
    }*/

    /*
    1	MODE_APPEND
    This will append the new preferences with the already existing preferences

    2	MODE_ENABLE_WRITE_AHEAD_LOGGING
    Database open flag. When it is set , it would enable write ahead logging by default

    3	MODE_MULTI_PROCESS
    This method will check for modification of preferences even if the sharedpreference instance has already been loaded

    4	MODE_PRIVATE
    By setting this mode , the file can only be accessed using calling application

    5	MODE_WORLD_READABLE
    This mode allow other application to read the preferences

    6	MODE_WORLD_WRITEABLE
    This mode allow other application to write the preferences
    */
}
