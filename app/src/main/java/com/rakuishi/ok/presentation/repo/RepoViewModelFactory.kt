package com.rakuishi.ok.presentation.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rakuishi.ok.data.GitHubRepository

/**
 * Factory for creating a [RepoViewModel] with a constructor
 */
class RepoViewModelFactory(
    private val gitHubRepository: GitHubRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepoViewModel(gitHubRepository) as T
    }
}