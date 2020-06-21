package com.rakuishi.ok.util

import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

fun OkHttpClient.request(url: String): Observable<Response> {
    val request: Request = Request.Builder()
        .url(url)
        .get()
        .build()

    return Observable.create {
        try {
            val response = newCall(request).execute()
            Observable.just(response)
            it.onNext(response)
            it.onComplete()
        } catch (exception: IOException) {
            it.onError(exception)
        }
    }
}