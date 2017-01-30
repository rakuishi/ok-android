package com.rakuishi.ok.ui.repo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakuishi.ok.R;
import com.rakuishi.ok.databinding.FragmentRepoBinding;
import com.rakuishi.ok.ui.base.BaseFragment;

import javax.inject.Inject;

public class RepoFragment extends BaseFragment {

    @Inject
    RepoViewModel viewModel;

    FragmentRepoBinding binding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
        viewModel.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentRepoBinding.bind(getView());
        binding.setViewModel(viewModel);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel.onDestroy();
    }
}
