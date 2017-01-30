package com.rakuishi.ok.ui.feed;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.util.LogUtil;
import com.rakuishi.ok.util.RxUtil;
import com.rakuishi.ok.ui.base.BaseViewModel;

import javax.inject.Inject;

public class FeedViewModel extends BaseViewModel {

    OkAPIClient client;

    @Inject
    public FeedViewModel(OkAPIClient client) {
        this.client = client;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        compositeDisposable.add(
                client.requestFeed()
                        .compose(RxUtil.applyMainSchedulers())
                        .subscribe(feed -> {
                            LogUtil.d(feed.title);
                        })
        );
    }
}
