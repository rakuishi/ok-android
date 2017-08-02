package com.rakuishi.ok.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.model.Gist;
import com.rakuishi.ok.util.IntentUtil;
import com.rakuishi.ok.util.RxUtil;

import javax.inject.Inject;

public class GistViewModel extends BaseViewModel {

    private Context context;
    private OkAPIClient client;

    public ObservableArrayList<Gist> gists = new ObservableArrayList<>();
    public ObservableBoolean isRefreshing;

    @Inject
    GistViewModel(Context context, OkAPIClient client) {
        this.context = context;
        this.client = client;
        isRefreshing = new ObservableBoolean(false);
    }

    public void refreshData() {
        isRefreshing.set(true);
        compositeDisposable.add(
                client.requestGists()
                        .compose(RxUtil.applyMainSchedulers())
                        .subscribe(gists -> {
                            this.gists.clear();
                            this.gists.addAll(gists);
                            isRefreshing.set(false);
                        })
        );
    }

    public void onItemClick(int position) {
        IntentUtil.startActivity(context, gists.get(position).url);
    }
}
