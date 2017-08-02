package com.rakuishi.ok.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.model.Repo;
import com.rakuishi.ok.util.IntentUtil;
import com.rakuishi.ok.util.RxUtil;

import javax.inject.Inject;

public class RepoViewModel extends BaseViewModel {

    private Context context;
    private OkAPIClient client;

    public ObservableArrayList<Repo> repos = new ObservableArrayList<>();
    public ObservableBoolean isRefreshing;

    @Inject
    RepoViewModel(Context context, OkAPIClient client) {
        this.context = context;
        this.client = client;
        isRefreshing = new ObservableBoolean(false);
    }

    public void refreshData() {
        isRefreshing.set(true);
        compositeDisposable.add(
                client.requestRepos()
                        .compose(RxUtil.applyMainSchedulers())
                        .subscribe(repos -> {
                            this.repos.clear();
                            this.repos.addAll(repos);
                            isRefreshing.set(false);
                        })
        );
    }

    public void onItemClick(int position) {
        IntentUtil.startActivity(context, repos.get(position).url);
    }
}
