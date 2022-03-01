package com.example.argent.presentation.list

import androidx.lifecycle.MutableLiveData
import com.example.argent.data.service.model.remote.response.GetTokensResponseModel
import com.example.argent.di.base.BaseViewModel
import com.example.argent.usecase.GetTokensUseCase
import com.example.argent.utils.subscribeResult
import javax.inject.Inject

class TokenListViewModel @Inject constructor(
    private val getTokensUseCase: GetTokensUseCase
) : BaseViewModel() {
    val tokensLiveData = MutableLiveData<GetTokensResponseModel>()
    fun getTokens() {
        getTokensUseCase.execute().subscribeResult(
            subscriptions,
            { result ->
                tokensLiveData.postValue(result)
            },
            {
                onError.onNext(it?.message?:" ")
            }
        )
    }
}