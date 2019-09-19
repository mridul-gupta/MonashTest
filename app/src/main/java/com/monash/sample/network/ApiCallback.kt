package com.monash.sample.network

interface ApiCallback<V> {
    fun onFailure()

    fun onSuccess(data: V)
}