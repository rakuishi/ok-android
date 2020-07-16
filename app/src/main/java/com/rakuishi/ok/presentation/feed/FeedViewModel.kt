package com.rakuishi.ok.presentation.feed

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rakuishi.ok.data.Feed
import com.rakuishi.ok.data.FeedRepository
import com.rakuishi.ok.util.RequestLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FeedViewModel @ViewModelInject constructor(
    private val feedRepository: FeedRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val requestLiveData: RequestLiveData<List<Feed>> = RequestLiveData()

    init {
        requestFeeds()
    }

    fun requestFeeds() {
        requestLiveData.setLoading()

        compositeDisposable.add(
            feedRepository.requestFeeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { feeds, throwable ->
                    if (throwable == null) {
                        requestLiveData.setSuccess(feeds)
                    } else {
                        requestLiveData.setFailure(throwable)
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}