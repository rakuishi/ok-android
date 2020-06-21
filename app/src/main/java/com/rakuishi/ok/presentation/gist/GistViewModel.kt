package com.rakuishi.ok.presentation.gist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rakuishi.ok.data.Gist
import com.rakuishi.ok.data.GitHubRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GistViewModel internal constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val gists: MutableLiveData<List<Gist>> = MutableLiveData(arrayListOf())
    val throwable: MutableLiveData<Throwable> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    init {
        requestGists()
    }

    private fun requestGists() {
        isLoading.value = true

        compositeDisposable.add(
            gitHubRepository.requestGists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repos, throwable ->
                    this.isLoading.value = false

                    if (throwable == null) {
                        this.gists.value = repos
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