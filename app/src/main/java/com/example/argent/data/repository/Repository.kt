package com.example.argent.data.repository

import com.example.argent.data.service.ApiServices
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiServices: ApiServices
) {
    fun getTokens(limit: Int? = 100, apiKey: String? = "freekey") =
        apiServices.getTokens(limit, apiKey)
}