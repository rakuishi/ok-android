package com.rakuishi.ok.viewmodel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel {

    public CompositeDisposable compositeDisposable;

    public BaseViewModel() {
        compositeDisposable = new CompositeDisposable();
    }

    public void onCreate() {
        // do something...
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }
}
