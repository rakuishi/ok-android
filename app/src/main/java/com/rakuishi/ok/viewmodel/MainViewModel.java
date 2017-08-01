package com.rakuishi.ok.viewmodel;

import android.support.annotation.NonNull;
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

    private BaseActivity activity;

    private static final String FRAGMENT_TAG_FEED = "feed";
    private static final String FRAGMENT_TAG_REPO = "repo";
    private static final String FRAGMENT_TAG_GIST = "gist";
    private FeedFragment feedFragment;
    private RepoFragment repoFragment;
    private GistFragment gistFragment;

    @Inject
    public MainViewModel(BaseActivity activity) {
        this.activity = activity;

        final FragmentManager manager = activity.getSupportFragmentManager();
        feedFragment = (FeedFragment) manager.findFragmentByTag(FRAGMENT_TAG_FEED);
        repoFragment = (RepoFragment) manager.findFragmentByTag(FRAGMENT_TAG_REPO);
        gistFragment = (GistFragment) manager.findFragmentByTag(FRAGMENT_TAG_GIST);

        if (feedFragment == null) {
            feedFragment = new FeedFragment();
        }
        if (repoFragment == null) {
            repoFragment = new RepoFragment();
        }
        if (gistFragment == null) {
            gistFragment = new GistFragment();
        }
    }

    public void replaceFragment(int itemId) {
        switch (itemId) {
            case R.id.action_feed:
                replaceFragment(feedFragment, FRAGMENT_TAG_FEED);
                break;
            case R.id.action_repo:
                replaceFragment(repoFragment, FRAGMENT_TAG_REPO);
                break;
            case R.id.action_gist:
                replaceFragment(gistFragment, FRAGMENT_TAG_GIST);
                break;
        }
    }

    private void replaceFragment(@NonNull Fragment fragment, String tag) {
        if (fragment.isAdded()) {
            return;
        }

        final FragmentManager manager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        final Fragment currentFragment = manager.findFragmentById(R.id.container);
        if (currentFragment != null) {
            transaction.detach(currentFragment);
        }
        if (fragment.isDetached()) {
            transaction.attach(fragment);
        } else {
            transaction.add(R.id.container, fragment, tag);
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // region BottomNavigationView.OnNavigationItemSelectedListener

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        replaceFragment(item.getItemId());
        return true;
    }

    // endregion
}
