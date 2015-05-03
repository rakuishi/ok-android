package com.rakuishi.ok.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakuishi.ok.R;
import com.rakuishi.ok.ui.common.SlidingTabLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {

    private static final String TAG = ViewPagerFragment.class.getSimpleName();

    @InjectView(R.id.sliding_tab_layout) SlidingTabLayout mTabLayout;
    @InjectView(R.id.view_pager) ViewPager mViewPager;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        ButterKnife.inject(this, view);

        ActionBarActivity activity = (ActionBarActivity)getActivity();
        activity.setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        setHasOptionsMenu(true);

        ViewPagerAdapter adapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mTabLayout.setDistributeEvenly(true);
        mTabLayout.setSelectedIndicatorColors(activity.getResources().getColor(R.color.myPrimaryDarkColor));
        mTabLayout.setViewPager(mViewPager);

        return view;
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FeedListFragment();
                case 1:
                    return new FeedListFragment();
                default:
                    return new FeedListFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Blog";
                case 1:
                    return "Repo";
                default:
                    return "Gist";
            }
        }
    }
}
