package com.rakuishi.ok.data

import com.rakuishi.ok.presentation.gist.GistViewModelFactory
import com.rakuishi.ok.presentation.repo.RepoViewModelFactory

object Injector {

    private fun getGitHubRepository(): GitHubRepository {
        return GitHubRepository.getInstance()
    }

    fun provideRepoViewModelFactory(): RepoViewModelFactory {
        return RepoViewModelFactory(getGitHubRepository())
    }

    fun provideGistViewModelFactory(): GistViewModelFactory {
        return GistViewModelFactory(getGitHubRepository())
    }
}