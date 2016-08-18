package com.cc.calculator.utils;

import android.os.Environment;

import com.cc.calculator.MyApplication;

import java.io.File;


/**
 * Created by lzw on 14-9-19.
 */
public class PathUtils {

  private static boolean isExternalStorageWritable() {
    String state = Environment.getExternalStorageState();
    return Environment.MEDIA_MOUNTED.equals(state);
  }

  private static File getAvailableCacheDir() {
    if (isExternalStorageWritable()) {
      return MyApplication.getInstance().getExternalCacheDir();
    } else {
      return MyApplication.getInstance().getCacheDir();
    }
  }


  public static String getAvatarCropPath() {
    return new File(getAvailableCacheDir(), "avatar_crop").getAbsolutePath();
  }

  public static String getAvatarTmpPath() {
    return new File(getAvailableCacheDir(), "avatar_tmp").getAbsolutePath();
  }

}
