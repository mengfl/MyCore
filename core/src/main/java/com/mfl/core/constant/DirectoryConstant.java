package com.mfl.core.constant;

import android.content.Context;

import com.mfl.core.utils.FileUtil;

import java.io.File;

public class DirectoryConstant {

    public static String PATH_ROOT; // 根目录
    public static String PATH_LOG; // 日志目录
    public static String PATH_CRASH; // 崩溃目录
    public static String PATH_PIC; // 图片目录
    public static String SOFT_NAME;



    /**
     * 定义软件目录
     *
     * @param context
     */
    public static void init(Context context,String softName) {
        SOFT_NAME=softName;
        PATH_ROOT = FileUtil.getSDCardPath()
                .concat(File.separator).concat(softName);
        PATH_LOG = PATH_ROOT.concat(File.separator).concat("log");
        PATH_CRASH = PATH_ROOT.concat(File.separator).concat("crash");
        PATH_PIC=PATH_ROOT.concat(File.separator).concat("pic");


        File dir = new File(PATH_ROOT);
        if (!dir.exists())
            dir.mkdirs();

        dir = new File(PATH_LOG);
        if (!dir.exists()) dir.mkdirs();

        dir = new File(PATH_PIC);
        if (!dir.exists()) dir.mkdirs();

        dir = new File(PATH_CRASH);
        if (!dir.exists()) dir.mkdirs();

    }
}
