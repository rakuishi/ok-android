package com.rakuishi.ok.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakuishi.ok.R;
import com.rakuishi.ok.adapter.FeedAdapter;
import com.rakuishi.ok.databinding.FragmentFeedBinding;
import com.rakuishi.ok.viewmodel.FeedViewModel;

import javax.inject.Inject;

public class FeedFragment extends BaseFragment {

    @Inject
    FeedViewModel viewModel;

    FragmentFeedBinding binding;
    FeedAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = FragmentFeedBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        viewModel.onCreate();

        setActionBarTitle(R.string.feed_title);

        adapter = new FeedAdapter(getActivity(), viewModel.feedItems);
        binding.listview.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.refreshData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel.onDestroy();
    }
}
