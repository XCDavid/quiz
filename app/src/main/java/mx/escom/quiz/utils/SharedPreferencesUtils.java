package mx.escom.quiz.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;


public class SharedPreferencesUtils {
    public static final String PREF_FILE_NAME = "pref_escom_quiz";
    //DATOS INICIALES
    public static final String TEST_INITIAL = "test_initial";
    public static final String TEST_COUNT = "test_count";
    public static final String TEST_AUX_NAME = "test_";

    public static void saveToPreferencesString(Context contex, String preferenceName, String value) {
        SharedPreferences sharedPreferences = contex.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, value);
        editor.apply();
    }

    public static String readFromPreferencesString(Context contex, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = contex.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }
    public static void saveToPreferencesBoolean(Context contex, String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = contex.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(preferenceName, defaultValue);
        editor.apply();
    }

    public static boolean readFromPreferencesBoolean(Context contex, String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = contex.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }
    public static void saveToPreferencesInt(Context contex, String preferenceName, int defaultValue) {
        SharedPreferences sharedPreferences = contex.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(preferenceName, defaultValue);
        editor.apply();
    }

    public static int readFromPreferencesInt(Context contex, String preferenceName, int defaultValue) {
        SharedPreferences sharedPreferences = contex.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(preferenceName, defaultValue);
    }

    public static void deleteFromPreferences(Context contex, String preferenceName) {
        SharedPreferences sharedPreferences = contex.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(preferenceName);
        editor.apply();
    }
}
