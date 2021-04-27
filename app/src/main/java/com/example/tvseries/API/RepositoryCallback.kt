package com.example.tvseries.API

interface RepositoryCallback<T> {
    fun onError()
    fun onSuccess(data: T)
}