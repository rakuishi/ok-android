package com.rakuishi.ok.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.model.FeedItem;
import com.rakuishi.ok.util.IntentUtil;
import com.rakuishi.ok.util.RxUtil;
import com.rakuishi.ok.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class FeedViewModel extends BaseViewModel {

    Context context;
    OkAPIClient client;

    public ObservableArrayList<FeedItem> feedItems = new ObservableArrayList<>();

    @Inject
    public FeedViewModel(Context context, OkAPIClient client) {
        this.context = context;
        this.client = client;
    }

    public void refreshData() {
        compositeDisposable.add(
                client.requestFeed()
                        .compose(RxUtil.applyMainSchedulers())
                        .subscribe(feed -> {
                            this.feedItems.clear();
                            this.feedItems.addAll(feed.list);
                        })
        );
    }

    public void onItemClick(int position) {
        IntentUtil.startActivity(context, feedItems.get(position).link);
    }
}
