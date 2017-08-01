package com.rakuishi.ok.di;

import com.rakuishi.ok.fragment.FeedFragment;
import com.rakuishi.ok.fragment.GistFragment;
import com.rakuishi.ok.fragment.RepoFragment;

import dagger.Subcomponent;

@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(FeedFragment fragment);
    void inject(GistFragment fragment);
    void inject(RepoFragment fragment);
}
