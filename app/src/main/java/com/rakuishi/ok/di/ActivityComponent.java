package com.rakuishi.ok.di;

import com.rakuishi.ok.activity.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
}
