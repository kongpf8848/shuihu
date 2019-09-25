package com.github.kongpf8848.shuihu.activity;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    public static void putInt(Context context, String filename, String key,int value){
        SharedPreferences sharedPreferences=context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key,value).commit();
    }
    public static int getInt(Context context, String filename, String key,int defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,defaultValue);
    }
    public static void putString(Context context, String filename, String key,String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value).commit();
    }
    public static String getString(Context context, String filename, String key,String defaultValue){
        SharedPreferences sharedPreferences=context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,defaultValue);
    }
}
