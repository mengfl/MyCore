package com.mfl.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mfl.core.constant.DirectoryConstant;


/**
 *  首选项工具  保存信息 到文件fileName中
 * Created by Administrator on 2016/5/14.
 */
public class PreferenceUtil {
  public static void write(Context context, String k, long v) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putLong(k, v);
        editor.commit();
    }
    public static void write(Context context, String k, int v) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(k, v);
        editor.commit();
    }
    public static void write(Context context, String fileName,String k, int v) {
        try {
            SharedPreferences preference = context.getSharedPreferences(fileName, 0);
            SharedPreferences.Editor editor = preference.edit();
            editor.putInt(k, v);
            editor.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void write(Context context, String k, boolean v) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(k, v);
        editor.commit();
    }
    public static void write(Context context, String fileName,String k, boolean v) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(k, v);
        editor.commit();
    }


    public static void write(Context context, String k, String v) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(k, v);
        editor.commit();
    }
    public static void write(Context context, String fileName,String k, String v) {
        try {
            SharedPreferences preference = context.getSharedPreferences(fileName, 0);
            SharedPreferences.Editor editor = preference.edit();
            editor.putString(k, v);
            editor.commit();
        }catch (Exception e){
            Log.e("TAG", "context="+context+", fileName = "+fileName+", key = "+k+", value = "+v);
            e.printStackTrace();
        }

    }

    public static int readInt(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        return preference.getInt(k, 0);
    }
    public static long readLong(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        return preference.getLong(k, -1L);
    }
    public static int readInt(Context context, String fileName,String k,int defv) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getInt(k, defv);
    }
    public static int readInt(Context context, String k, int defv) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        return preference.getInt(k, defv);
    }



    public static boolean readBoolean(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        return preference.getBoolean(k, false);
    }
    public static boolean readBoolean(Context context, String fileName,String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        return preference.getBoolean(k, false);
    }
    public static boolean readBoolean(Context context, String k, boolean defBool) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        return preference.getBoolean(k, defBool);
    }



    public static String readString(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        return preference.getString(k, (String)"");
    }
    public static String readString(Context context, String fileName,String k) {
        try {
            SharedPreferences preference = context.getSharedPreferences(fileName, 0);
            return preference.getString(k, "");
        }catch (Exception e){
            Log.e("TAG", "context="+context+", fileName = "+fileName+", key = "+k);
            e.printStackTrace();
        }

        return "";
    }


    public static void remove(Context context, String k) {
        SharedPreferences preference = context.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.remove(k);
        editor.commit();
    }
    public static void remove(Context context, String fileName,String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.remove(k);
        editor.commit();
    }

    public static void clean(Context cxt) {
        SharedPreferences preference = cxt.getSharedPreferences(DirectoryConstant.SOFT_NAME, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.commit();
    }
    public static void clean(Context cxt,String fileName) {
        SharedPreferences preference = cxt.getSharedPreferences(fileName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.commit();
    }



}
