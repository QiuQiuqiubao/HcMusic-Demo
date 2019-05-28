package com.example.asus.qqapp.Util;

import android.os.Environment;

import java.io.File;

/**
 * Created by asus on 2018/1/18.
 */

public class SDCardUtil {
    public static boolean isLocal(String songid) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MyMusic" + File.separator + songid + ".mp3";
            File file = new File(filePath);
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }
}
