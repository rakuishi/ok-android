package com.rakuishi.ok.di;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import org.simpleframework.xml.core.Persister;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class AppModule {

    private Context context;

    public AppModule(Application app) {
        this.context = app;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    public OkHttpClient provideHttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    public Persister providePersister() {
        return new Persister();
    }
}
