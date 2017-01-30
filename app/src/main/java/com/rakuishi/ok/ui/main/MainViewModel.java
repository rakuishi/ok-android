package com.rakuishi.ok.ui.main;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.rakuishi.ok.R;
import com.rakuishi.ok.ui.base.BaseActivity;
import com.rakuishi.ok.ui.base.BaseViewModel;
import com.rakuishi.ok.ui.feed.FeedFragment;
import com.rakuishi.ok.ui.gist.GistFragment;
import com.rakuishi.ok.ui.repo.RepoFragment;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    private BaseActivity activity;

    @Inject
    public MainViewModel(BaseActivity activity) {
        this.activity = activity;
    }

    public void replaceFragment(@IdRes int tabId) {
        FragmentTransaction transaction = activity.getSupportFragmentManager()
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
