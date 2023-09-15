package com.mahdicen.knowmovies.util

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.asyncRequest() :Single<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.asyncRequest() :Completable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}