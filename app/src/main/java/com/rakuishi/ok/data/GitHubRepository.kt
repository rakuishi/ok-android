package com.rakuishi.ok.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class GitHubRepository {

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: GitHubRepository? = null

        fun getInstance(): GitHubRepository {
            return instance ?: synchronized(this) {
                instance ?: GitHubRepository().also { instance = it }
            }
        }
    }

    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val gson: Gson = Gson()

    private fun request(url: String): Observable<Response> {
        val request: Request = Request.Builder()
            .url(url)
            .get()
            .build()

        return Observable.create {
            try {
                val response = okHttpClient.newCall(request).execute()
                Observable.just(response)
                it.onNext(response)
                it.onComplete()
            } catch (exception: IOException) {
                it.onError(exception)
            }
        }
    }

    fun requestRepos(): Single<List<Repo>> {
        return request("https://api.github.com/users/rakuishi/repos?sort=updated")
            .map {
                val type = object : TypeToken<List<Repo>>() {}.type
                gson.fromJson<List<Repo>>(it.body()?.string() ?: "[]", type)
            }
            .singleOrError()
    }

    fun requestGists(): Single<List<Gist>> {
        return request("https://api.github.com/users/rakuishi/gists?sort=updated")
            .map {
                val type = object : TypeToken<List<Gist>>() {}.type
                gson.fromJson<List<Gist>>(it.body()?.string() ?: "[]", type)
            }
            .singleOrError()
    }
}