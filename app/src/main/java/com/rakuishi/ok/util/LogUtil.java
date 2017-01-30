package com.rakuishi.ok.util;

import android.util.Log;

import com.rakuishi.ok.BuildConfig;

public class LogUtil {

    public static void d(String message) {
        if (BuildConfig.DEBUG) Log.d("com.rakuishi.ok", message);
    }
}
