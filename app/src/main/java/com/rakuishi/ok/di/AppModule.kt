package com.rakuishi.ok.di

import com.rakuishi.ok.data.FeedRepository
import com.rakuishi.ok.data.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideFeedRepository() = FeedRepository()

    @Singleton
    @Provides
    fun provideGitHubRepository() = GitHubRepository()
}