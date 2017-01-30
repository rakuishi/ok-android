package com.rakuishi.ok.view.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rakuishi.ok.R;
import com.rakuishi.ok.databinding.ActivityMainBinding;
import com.rakuishi.ok.view.fragment.FeedFragment;
import com.rakuishi.ok.view.fragment.GistFragment;
import com.rakuishi.ok.view.fragment.RepoFragment;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.bottombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                replaceFragment(tabId);
            }
        });
    }

    private void replaceFragment(@IdRes int tabId) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        Fragment fragment;

        switch (tabId) {
            case R.id.tab_feed:
                fragment = new FeedFragment();
                break;
            case R.id.tab_repo:
                fragment = new RepoFragment();
                break;
            case R.id.tab_gist:
            default:
                fragment = new GistFragment();
                break;
        }

        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
