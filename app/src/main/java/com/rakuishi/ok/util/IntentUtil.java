package com.rakuishi.ok.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class IntentUtil {

    public static void startActivity(Context context, String url) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
