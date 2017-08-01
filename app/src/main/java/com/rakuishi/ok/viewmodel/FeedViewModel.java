package com.rakuishi.ok.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.model.FeedItem;
import com.rakuishi.ok.util.IntentUtil;
import com.rakuishi.ok.util.RxUtil;

import javax.inject.Inject;

public class FeedViewModel extends BaseViewModel {

    private Context context;
    private OkAPIClient client;

    public ObservableArrayList<FeedItem> feedItems = new ObservableArrayList<>();
    public ObservableBoolean isRefreshing;

    @Inject
    FeedViewModel(Context context, OkAPIClient client) {
        this.context = context;
        this.client = client;
        isRefreshing = new ObservableBoolean(false);
    }

    public void refreshData() {
        isRefreshing.set(true);
        compositeDisposable.add(
                client.requestFeed()
                        .compose(RxUtil.applyMainSchedulers())
                        .subscribe(feed -> {
                            this.feedItems.clear();
                            this.feedItems.addAll(feed.list);
                            isRefreshing.set(false);
                        }));
    }

    public void onItemClick(int position) {
        IntentUtil.startActivity(context, feedItems.get(position).link);
    }
}
