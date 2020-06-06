package com.example.match.Tool;

import android.app.Activity;
import android.content.Context;

public class DataTool {

    public static String getUserName(Activity activity){
        return activity.getSharedPreferences("user", Context.MODE_PRIVATE).getString("username","");
    }
}
