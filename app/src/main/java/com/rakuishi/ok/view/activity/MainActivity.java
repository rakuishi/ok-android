package com.rakuishi.ok.view.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.IdRes;
import android.os.Bundle;

import com.rakuishi.ok.R;
import com.rakuishi.ok.databinding.ActivityMainBinding;
import com.rakuishi.ok.viewmodel.MainViewModel;
import com.roughike.bottombar.OnTabSelectListener;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    MainViewModel viewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);

        // To be honest, I don't want to write `setOnTabSelectListener` in this MainActivity.
        // But BottomBar doesn't provide a data-bindable listener.
        binding.bottombar.setOnTabSelectListener(tabId -> viewModel.replaceFragment(tabId));
    }
}