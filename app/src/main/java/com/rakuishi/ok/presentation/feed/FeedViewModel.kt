package com.rakuishi.ok.presentation.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rakuishi.ok.data.Feed
import com.rakuishi.ok.data.FeedRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FeedViewModel internal constructor(
    private val feedRepository: FeedRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val feeds: MutableLiveData<List<Feed>> = MutableLiveData(arrayListOf())
    val throwable: MutableLiveData<Throwable> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    init {
        requestFeeds()
    }

    private fun requestFeeds() {
        isLoading.value = true

        compositeDisposable.add(
            feedRepository.requestFeeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { feeds, throwable ->
                    this.isLoading.value = false

                    if (throwable == null) {
                        this.feeds.value = feeds
                    } else {
                        this.throwable.value = throwable
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}