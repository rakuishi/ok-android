package com.rakuishi.ok.presentation.repo

import androidx.lifecycle.ViewModel
import com.rakuishi.ok.data.GitHubRepository
import com.rakuishi.ok.data.Repo
import com.rakuishi.ok.util.RequestLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RepoViewModel internal constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val requestLiveData: RequestLiveData<List<Repo>> = RequestLiveData()

    init {
        requestRepos()
    }

    fun requestRepos() {
        requestLiveData.setLoading()

        compositeDisposable.add(
            gitHubRepository.requestRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repos, throwable ->
                    if (throwable == null) {
                        requestLiveData.setSuccess(repos)
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