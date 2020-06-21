package com.rakuishi.ok.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rakuishi.ok.data.FeedRepository

/**
 * Factory for creating a [FeedViewModel] with a constructor
 */
class FeedViewModelFactory(
    private val feedRepository: FeedRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeedViewModel(feedRepository) as T
    }
}