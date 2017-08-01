package com.rakuishi.ok.viewmodel;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.rakuishi.ok.R;
import com.rakuishi.ok.activity.BaseActivity;
import com.rakuishi.ok.fragment.FeedFragment;
import com.rakuishi.ok.fragment.GistFragment;
import com.rakuishi.ok.fragment.RepoFragment;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_COUNT = 3;
    private static final int CONTAINER_ID = R.id.container;
    private BaseActivity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Inject
    MainViewModel(BaseActivity activity) {
        this.activity = activity;
        fragmentManager = activity.getSupportFragmentManager();
    }

    @Override
    public void onDestroy() {
        for (int i = 0; i < FRAGMENT_COUNT; i++) {
            destroyFragment(i);
        }
        commitTransaction();
        super.onDestroy();
    }

    @SuppressLint("CommitTransaction")
    public void instantiateFragment(int position) {
        if (transaction == null) {
            transaction = fragmentManager.beginTransaction();
        }

        String name = makeFragmentName(position);
        Fragment fragment = fragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            transaction.attach(fragment);
        } else {
            fragment = getFragment(position);
            transaction.add(R.id.container, fragment, name);
        }

        for (int i = 0; i < FRAGMENT_COUNT; i++) {
            if (i != position) {
                destroyFragment(i);
            }
        }

        setVisibility(fragment, true);
        commitTransaction();
    }

    @SuppressLint("CommitTransaction")
    private void destroyFragment(int position) {
        if (transaction == null) {
            transaction = fragmentManager.beginTransaction();
        }

        String name = makeFragmentName(position);
        Fragment fragment = fragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            transaction.detach(fragment);
            setVisibility(fragment, false);
        }
    }

    private void commitTransaction() {
        if (transaction != null) {
            transaction.commitNowAllowingStateLoss();
            transaction = null;
        }
    }

    private void setVisibility(@Nullable Fragment fragment, boolean visible) {
        if (fragment != null) {
            fragment.setUserVisibleHint(visible);
            fragment.setMenuVisibility(visible);
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
        instantiateFragment(position);
        return true;
    }

    // endregion
}
