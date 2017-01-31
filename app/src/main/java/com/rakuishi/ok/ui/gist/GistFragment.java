package com.rakuishi.ok.ui.gist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakuishi.ok.R;
import com.rakuishi.ok.databinding.FragmentGistBinding;
import com.rakuishi.ok.ui.base.BaseFragment;

import javax.inject.Inject;

public class GistFragment extends BaseFragment {

    @Inject
    GistViewModel viewModel;

    FragmentGistBinding binding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gist, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentGistBinding.bind(getView());
        binding.setViewModel(viewModel);
        viewModel.onCreate();
        setActionBarTitle(R.string.gist_title);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel.onDestroy();
    }
}
