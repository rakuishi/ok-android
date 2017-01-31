package com.rakuishi.ok.ui.base;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.rakuishi.ok.App;
import com.rakuishi.ok.di.FragmentComponent;
import com.rakuishi.ok.di.FragmentModule;

public class BaseFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    @NonNull
    public FragmentComponent getComponent() {
        if (fragmentComponent == null) {
            App app = (App) getContext().getApplicationContext();
            fragmentComponent = app.getComponent().fragmentComponent(new FragmentModule(this));
        }
        return fragmentComponent;
    }

    public void setActionBarTitle(@StringRes int resId) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle(resId);
    }
}
