package com.example.argent.di.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

open class BaseViewModel : ViewModel() {
    protected val subscriptions = CompositeDisposable()

    val onError = PublishSubject.create<String>()

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}