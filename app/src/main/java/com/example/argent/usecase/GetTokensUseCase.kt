package com.example.argent.usecase

import com.example.argent.data.repository.Repository
import javax.inject.Inject

class GetTokensUseCase @Inject constructor(private val repository: Repository) {
    fun execute(limit: Int? = 100, apiKey: String? = "freekey") =
        repository.getTokens(limit, apiKey)
}