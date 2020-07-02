package com.rakuishi.ok.presentation.gist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rakuishi.ok.data.GitHubRepository

/**
 * Factory for creating a [GistViewModel] with a constructor
 */
class GistViewModelFactory(
    private val gitHubRepository: GitHubRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GistViewModel(gitHubRepository) as T
    }
}