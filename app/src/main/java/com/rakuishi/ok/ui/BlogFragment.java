package com.rakuishi.ok.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakuishi.ok.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {

    public BlogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        ActionBarActivity activity = (ActionBarActivity)getActivity();
        activity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));

        return view;
    }
}
