package com.androdev.azadproject;

import android.app.Activity;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class Helpers {
    // Regex to check valid password.
    static String regex = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";

    public static Pattern getPassPatern(){
        return Pattern.compile(regex);
    }

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = ((Activity)context).getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
