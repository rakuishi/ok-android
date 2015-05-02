package com.rakuishi.ok.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.rakuishi.ok.R;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new BlogListFragment())
                .commit();
    }
}
