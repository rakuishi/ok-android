package com.rakuishi.ok.presentation.repo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rakuishi.ok.data.GitHubRepository
import com.rakuishi.ok.data.Repo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RepoViewModel internal constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val repos: MutableLiveData<List<Repo>> = MutableLiveData(arrayListOf())
    val throwable: MutableLiveData<Throwable> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    init {
        requestRepos()
    }

    private fun requestRepos() {
        isLoading.value = true

        compositeDisposable.add(
            gitHubRepository.requestRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repos, throwable ->
                    this.isLoading.value = false

                    if (throwable == null) {
                        this.repos.value = repos
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