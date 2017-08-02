package com.rakuishi.ok.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.rakuishi.ok.R;
import com.rakuishi.ok.databinding.ActivityMainBinding;
import com.rakuishi.ok.viewmodel.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    MainViewModel viewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        viewModel.onCreate();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(viewModel);

        viewModel.showFragmentAndCommitTransaction(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
