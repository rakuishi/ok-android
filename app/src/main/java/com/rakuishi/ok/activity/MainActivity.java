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

        // To be honest, I don't want to write `setOnNavigationItemSelectedListener` in this MainActivity.
        // But BottomNavigationView doesn't provide a data-bindable listener.
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            viewModel.replaceFragment(item.getItemId());
            return true;
        });

        if (savedInstanceState == null) {
            viewModel.replaceFragment(R.id.action_feed);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
