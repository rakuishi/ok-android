package com.rakuishi.ok;

import android.app.Application;
import android.support.annotation.NonNull;

import com.rakuishi.ok.di.AppComponent;
import com.rakuishi.ok.di.AppModule;
import com.rakuishi.ok.di.DaggerAppComponent;

public class App extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public AppComponent getComponent() {
        return appComponent;
    }
}
