package com.rakuishi.ok.viewmodel;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.util.LogUtil;
import com.rakuishi.ok.util.RxUtil;

import javax.inject.Inject;

public class GistViewModel extends BaseViewModel {

    OkAPIClient client;

    @Inject
    public GistViewModel(OkAPIClient client) {
        this.client = client;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        compositeDisposable.add(
                client.requestGists()
                        .compose(RxUtil.applyMainSchedulers())
                        .subscribe(gists -> {
                            LogUtil.d(gists.get(0).url);
                        })
        );
    }
}
