package com.rakuishi.ok.view.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.rakuishi.ok.App;
import com.rakuishi.ok.di.ActivityComponent;
import com.rakuishi.ok.di.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @NonNull
    public ActivityComponent getComponent() {
        if (activityComponent == null) {
            App app = (App) getApplication();
            activityComponent = app.getComponent().activityComponent(new ActivityModule(this));
        }
        return activityComponent;
    }
}
