package com.rakuishi.ok.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.rakuishi.ok.R;
import com.rakuishi.ok.api.OkAPIClient;
import com.rakuishi.ok.api.model.Gist;
import com.rakuishi.ok.api.model.Repo;
import com.rakuishi.ok.util.ToastUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by rakuishi on 15/05/03.
 */
public class GistListFragment extends Fragment {

    public static final String TAG = FeedListFragment.class.getSimpleName();

    private CompositeSubscription mSubscription = new CompositeSubscription();

    @InjectView(R.id.list_lv)
    ListView mListView;
    @InjectView(R.id.list_empty_view)
    FrameLayout mEmptyView;

    @OnItemClick(R.id.list_lv)
    void onItemClick(int position) {
        // FeedItem item = (FeedItem) mListView.getAdapter().getItem(position);
        // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink())));
    }

    public GistListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, view);

        mListView.setEmptyView(mEmptyView);
        requestGist();
        return view;
    }

    @Override
    public void onDestroy() {
        mSubscription.unsubscribe();
        super.onDestroy();
    }

    private void requestGist() {
        mSubscription.add(OkAPIClient.getInstance().requestGists()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Gist>>() {
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
                    public void onNext(List<Gist> gists) {
                        mListView.setAdapter(new GistAdapter(getActivity(), gists));
                    }
                }));
    }
}
