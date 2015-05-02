package com.rakuishi.ok.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by rakuishi on 15/05/02.
 */
public class ToastUtils {

    private ToastUtils() {
        // Empty private constructor
    }

    public static void showShortMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
