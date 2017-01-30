package com.rakuishi.ok.ui.repo;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.util.LogUtil;
import com.rakuishi.ok.util.RxUtil;
import com.rakuishi.ok.ui.base.BaseViewModel;

import javax.inject.Inject;

public class RepoViewModel extends BaseViewModel {

    OkAPIClient client;

    @Inject
    public RepoViewModel(OkAPIClient client) {
        this.client = client;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        compositeDisposable.add(
                client.requestRepos()
                        .compose(RxUtil.applyMainSchedulers())
                        .subscribe(repos -> {
                            LogUtil.d(repos.get(0).name);
                        })
        );
    }
}
