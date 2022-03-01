package com.example.argent.data.service

import com.example.argent.data.service.model.remote.response.GetTokensResponseModel
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServices {
    @GET("getTopTokens")
    fun getTokens(
        @Query("limit") id: Int? = 100,
        @Query("apiKey") apiKey: String? = "freekey"
    ): Observable<GetTokensResponseModel>
}