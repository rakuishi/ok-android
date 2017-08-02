package com.rakuishi.ok.viewmodel;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.rakuishi.ok.R;
import com.rakuishi.ok.activity.BaseActivity;
import com.rakuishi.ok.fragment.BaseFragment;
import com.rakuishi.ok.fragment.FeedFragment;
import com.rakuishi.ok.fragment.GistFragment;
import com.rakuishi.ok.fragment.RepoFragment;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_COUNT = 3;
    private static final int CONTAINER_ID = R.id.container;
    private BaseActivity activity;
    private FragmentManager fragmentManager;
    private int primaryItem = 0;

    @Inject
    MainViewModel(BaseActivity activity) {
        this.activity = activity;
        fragmentManager = activity.getSupportFragmentManager();
    }

    @Override
    @SuppressLint("CommitTransaction")
    public void onDestroy() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int position = 0; position < FRAGMENT_COUNT; position++) {
            String name = makeFragmentName(position);
            Fragment fragment = fragmentManager.findFragmentByTag(name);
            if (fragment != null) {
                transaction.remove(fragment);
            }
        }
        transaction.commitNowAllowingStateLoss();
        super.onDestroy();
    }

    @SuppressLint("CommitTransaction")
    public void showFragmentAndCommitTransaction(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        primaryItem = position;

        // hide
        for (int i = 0; i < FRAGMENT_COUNT; i++) {
            String name = makeFragmentName(i);
            Fragment fragment = fragmentManager.findFragmentByTag(name);
            if (i == position) {
                // show
                if (fragment != null) {
                    transaction.show(fragment);
                } else {
                    fragment = getFragment(position);
                    transaction.add(R.id.container, fragment, name);
                }
            } else {
                // hide
                if (fragment != null) {
                    transaction.hide(fragment);
                }
            }
        }

        transaction.commitNowAllowingStateLoss();
        updateVisibility();
    }

    private void updateVisibility() {
        for (int position = 0; position < FRAGMENT_COUNT; position++) {
            String name = makeFragmentName(position);
            Fragment fragment = fragmentManager.findFragmentByTag(name);
            if (fragment != null && fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).fireOnFragmentVisibilityChangedIfNeeded(primaryItem == position);
            }
        }
    }

    private String makeFragmentName(int position) {
        return "android:switcher:" + CONTAINER_ID + ":" + position;
    }

    private Fragment getFragment(int position) {
        switch (position) {
            case 0:
                return new FeedFragment();
            case 1:
                return new RepoFragment();
            case 2:
                return new GistFragment();
        }

        throw new IllegalStateException("This position:" + position + " is not supported.");
    }

    private int getPosition(int itemId) {
        switch (itemId) {
            case R.id.action_feed:
                return 0;
            case R.id.action_repo:
                return 1;
            case R.id.action_gist:
                return 2;
        }

        throw new IllegalStateException("This itemId:" + itemId + " is not supported.");
    }

    // region BottomNavigationView.OnNavigationItemSelectedListener

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int position = getPosition(item.getItemId());
        showFragmentAndCommitTransaction(position);
        return true;
    }

    // endregion
}
