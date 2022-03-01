package com.example.argent.utils

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.subscribeResult(
    subscription: CompositeDisposable,
    onSuccess: (data: T) -> Unit,
    onError: (throwable: Throwable?) -> Unit
) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            onSuccess(it)
        }, {
            it.printStackTrace()
            onError(it)
        }, {

        }).addTo(subscription)
}
