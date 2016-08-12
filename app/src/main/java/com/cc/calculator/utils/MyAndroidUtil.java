package com.cc.calculator.utils;

import android.content.SharedPreferences;

import com.cc.calculator.MyApplication;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/10 10:28
 * 修改人：Administrator
 * 修改时间：2016/8/10 10:28
 * 修改备注：
 */
public class MyAndroidUtil {


    /**
     * 修改缓存
     *
     * @param name   一般都name+   actid、或者userId
     * @param result 要保存的
     */
    public static void editXmlByString(String name, String result) {
        SharedPreferences.Editor editor = MyApplication.sharedPreferences.edit();
        if (MyApplication.sharedPreferences.getString(name, null) != null) {
            editor.remove(name);
        }
        editor.putString(name, result);
        editor.commit();
    }

    /**
     * 修改缓存
     *
     * @param name 一般都name+   actid、或者userId
     * @param
     */
    public static void editXml(String name, boolean is) {
        SharedPreferences.Editor editor = MyApplication.sharedPreferences.edit();
        editor.putBoolean(name, is);
        editor.commit();
    }


    public static void removeXml(String name) {
        SharedPreferences.Editor editor = MyApplication.sharedPreferences.edit();
        editor.remove(name);
        editor.commit();
    }

}
