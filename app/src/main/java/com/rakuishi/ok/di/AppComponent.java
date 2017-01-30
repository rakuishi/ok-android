package com.rakuishi.ok.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    ActivityComponent activityComponent(ActivityModule module);
    FragmentComponent fragmentComponent(FragmentModule module);
}
