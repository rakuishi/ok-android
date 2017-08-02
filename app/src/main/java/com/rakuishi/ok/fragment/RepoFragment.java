package com.rakuishi.ok.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakuishi.ok.R;
import com.rakuishi.ok.adapter.RepoAdapter;
import com.rakuishi.ok.databinding.FragmentRepoBinding;
import com.rakuishi.ok.viewmodel.RepoViewModel;

import javax.inject.Inject;

public class RepoFragment extends BaseFragment {

    @Inject
    RepoViewModel viewModel;

    FragmentRepoBinding binding;
    RepoAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = FragmentRepoBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        viewModel.onCreate();

        setActionBarTitle(R.string.repo_title);

        adapter = new RepoAdapter(getActivity(), viewModel.repos);
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
