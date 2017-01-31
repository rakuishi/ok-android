package com.rakuishi.ok.ui.repo;

import android.content.Context;
import android.databinding.ObservableArrayList;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.model.Repo;
import com.rakuishi.ok.util.IntentUtil;
import com.rakuishi.ok.util.RxUtil;
import com.rakuishi.ok.ui.base.BaseViewModel;

import javax.inject.Inject;

public class RepoViewModel extends BaseViewModel {

    Context context;
    OkAPIClient client;

    public ObservableArrayList<Repo> repos = new ObservableArrayList<>();

    @Inject
    public RepoViewModel(Context context, OkAPIClient client) {
        this.context = context;
        this.client = client;
    }

    public void refreshData() {
        compositeDisposable.add(
                client.requestRepos()
                        .compose(RxUtil.applyMainSchedulers())
                        .subscribe(repos -> {
                            this.repos.clear();
                            this.repos.addAll(repos);
                        })
        );
    }

    public void onItemClick(int position) {
        IntentUtil.startActivity(context, repos.get(position).url);
    }
}
