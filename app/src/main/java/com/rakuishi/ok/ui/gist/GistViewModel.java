package com.rakuishi.ok.ui.gist;

import android.content.Context;
import android.databinding.ObservableArrayList;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.model.Gist;
import com.rakuishi.ok.util.IntentUtil;
import com.rakuishi.ok.util.RxUtil;
import com.rakuishi.ok.ui.base.BaseViewModel;

import javax.inject.Inject;

public class GistViewModel extends BaseViewModel {

    Context context;
    OkAPIClient client;

    public ObservableArrayList<Gist> gists = new ObservableArrayList<>();

    @Inject
    public GistViewModel(Context context, OkAPIClient client) {
        this.context = context;
        this.client = client;
    }

    public void refreshData() {
        compositeDisposable.add(
                client.requestGists()
                        .compose(RxUtil.applyMainSchedulers())
                        .subscribe(gists -> {
                            this.gists.clear();
                            this.gists.addAll(gists);
                        })
        );
    }

    public void onItemClick(int position) {
        IntentUtil.startActivity(context, gists.get(position).url);
    }
}
