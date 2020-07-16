package com.rakuishi.ok.presentation.gist

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rakuishi.ok.data.Gist
import com.rakuishi.ok.data.GitHubRepository
import com.rakuishi.ok.util.RequestLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GistViewModel @ViewModelInject constructor(
    private val gitHubRepository: GitHubRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val requestLiveData: RequestLiveData<List<Gist>> = RequestLiveData()

    init {
        requestGists()
    }

    fun requestGists() {
        requestLiveData.setLoading()

        compositeDisposable.add(
            gitHubRepository.requestGists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gists, throwable ->
                    if (throwable == null) {
                        requestLiveData.setSuccess(gists)
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