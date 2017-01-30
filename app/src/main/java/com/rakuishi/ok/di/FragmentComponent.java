package com.rakuishi.ok.di;

import com.rakuishi.ok.ui.feed.FeedFragment;
import com.rakuishi.ok.ui.gist.GistFragment;
import com.rakuishi.ok.ui.repo.RepoFragment;

import dagger.Subcomponent;

@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(FeedFragment fragment);
    void inject(GistFragment fragment);
    void inject(RepoFragment fragment);
}
