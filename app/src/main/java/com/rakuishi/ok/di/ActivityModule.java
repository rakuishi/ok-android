package com.rakuishi.ok.di;

import com.rakuishi.ok.view.activity.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public BaseActivity provideActivity() {
        return activity;
    }
}
