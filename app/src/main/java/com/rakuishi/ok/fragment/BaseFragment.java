package com.rakuishi.ok.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakuishi.ok.App;
import com.rakuishi.ok.di.FragmentComponent;
import com.rakuishi.ok.di.FragmentModule;

public class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";
    private static final boolean DEBUG = true;
    private FragmentComponent fragmentComponent;
    private boolean visibility = false;
    private boolean shouldCheckVisibilityAfterOnResume = false;

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
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(resId);
        }
    }

    // region Lifecycle

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (DEBUG) Log.v(TAG, toString() + " onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) Log.v(TAG, toString() + " onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (DEBUG) Log.v(TAG, toString() + " onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (DEBUG) Log.v(TAG, toString() + " onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (DEBUG) Log.v(TAG, toString() + " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (DEBUG) Log.v(TAG, toString() + " onResume");
        if (shouldCheckVisibilityAfterOnResume) {
            shouldCheckVisibilityAfterOnResume = false;
            onFragmentVisibilityChanged(visibility);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (DEBUG) Log.v(TAG, toString() + " onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        if (DEBUG) Log.v(TAG, toString() + " onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (DEBUG) Log.v(TAG, toString() + " onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DEBUG) Log.v(TAG, toString() + " onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (DEBUG) Log.v(TAG, toString() + " onDetach");
    }

    // endregion

    public void updateFragmentVisibilityIfNeeded(boolean visibility) {
        if (visibility != this.visibility) {
            if (isResumed()) {
                onFragmentVisibilityChanged(visibility);
            } else {
                shouldCheckVisibilityAfterOnResume = true;
                this.visibility = visibility;
            }
        }
    }

    /**
     * `onFragmentVisibilityChanged` is called after `onResume`.
     */
    public void onFragmentVisibilityChanged(boolean visibility) {
        this.visibility = visibility;
        if (DEBUG) Log.v(TAG, toString() + " onFragmentVisibilityChanged: " + visibility);
    }
}
