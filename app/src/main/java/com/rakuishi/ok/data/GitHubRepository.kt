package com.rakuishi.ok.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rakuishi.ok.util.request
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient


class GitHubRepository {

    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val gson: Gson = Gson()

    fun requestRepos(): Single<List<Repo>> {
        return okHttpClient.request("https://api.github.com/users/rakuishi/repos?sort=updated")
            .map {
                val type = object : TypeToken<List<Repo>>() {}.type
                gson.fromJson<List<Repo>>(it.body()?.string() ?: "[]", type)
            }
            .singleOrError()
    }

    fun requestGists(): Single<List<Gist>> {
        return okHttpClient.request("https://api.github.com/users/rakuishi/gists?sort=updated")
            .map {
                val type = object : TypeToken<List<Gist>>() {}.type
                gson.fromJson<List<Gist>>(it.body()?.string() ?: "[]", type)
            }
            .singleOrError()
    }
}