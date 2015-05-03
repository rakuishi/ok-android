package com.rakuishi.ok.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.rakuishi.ok.R;
import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.api.model.Feed;
import com.rakuishi.ok.api.model.FeedItem;
import com.rakuishi.ok.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedListFragment extends Fragment {

    public static final String TAG = FeedListFragment.class.getSimpleName();

    private CompositeSubscription mSubscription = new CompositeSubscription();

    @InjectView(R.id.blog_lv) ListView mListView;
    @InjectView(R.id.blog_empty_view) FrameLayout mEmptyView;

    @OnItemClick(R.id.blog_lv)
    void onItemClick(int position) {
        FeedItem item = (FeedItem) mListView.getAdapter().getItem(position);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink())));
    }

    public FeedListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed_list, container, false);
        ButterKnife.inject(this, view);

        mListView.setEmptyView(mEmptyView);
        requestBlogFeed();
        return view;
    }

    @Override
    public void onDestroy() {
        mSubscription.unsubscribe();
        super.onDestroy();
    }

    private void requestBlogFeed() {
        mSubscription.add(OkAPIClient.getInstance().requestFeed()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<Feed>() {
                @Override
                public void onCompleted() {
                    mEmptyView.setVisibility(View.GONE);
                }

                @Override
                public void onError(Throwable e) {
                    ToastUtils.showLongMessage(getActivity(), e.getMessage());
                    mEmptyView.setVisibility(View.GONE);
                }

                @Override
                public void onNext(Feed feed) {
                    mListView.setAdapter(new FeedAdapter(getActivity(), feed.getList()));
                }
            })
        );
    }
}
