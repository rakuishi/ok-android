package com.rakuishi.ok.util

import androidx.lifecycle.MutableLiveData

class RequestLiveData<T> : MutableLiveData<RequestLiveData.Response<T>>() {

    class Response<T>(val state: State, val data: T? = null, val throwable: Throwable? = null)
    enum class State { LOADING, SUCCESS, FAILURE }

    fun setSuccess(data: T) {
        value = Response(State.SUCCESS, data)
    }

    fun postSuccess(data: T) {
        postValue(Response(State.SUCCESS, data))
    }

    fun setFailure(throwable: Throwable) {
        value = Response(state = State.FAILURE, throwable = throwable)
    }

    fun postFailure(throwable: Throwable) {
        postValue(Response(state = State.FAILURE, throwable = throwable))
    }

    fun setLoading() {
        value = Response(State.LOADING)
    }

    fun postLoading() {
        postValue(Response(State.LOADING))
    }
}