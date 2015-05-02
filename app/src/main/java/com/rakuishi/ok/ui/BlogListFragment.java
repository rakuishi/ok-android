package com.rakuishi.ok.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rakuishi.ok.R;
import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.api.model.Feed;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogListFragment extends Fragment {

    public static final String TAG = BlogListFragment.class.getSimpleName();

    private CompositeSubscription mSubscription = new CompositeSubscription();

    @InjectView(R.id.blog_lv) ListView mListView;

    public BlogListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        ButterKnife.inject(this, view);

        // Setup Toolbar
        ActionBarActivity activity = (ActionBarActivity)getActivity();
        activity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));

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

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Feed feed) {
                    mListView.setAdapter(new FeedAdapter(getActivity(), feed.getList()));
                }
            })
        );
    }
}
