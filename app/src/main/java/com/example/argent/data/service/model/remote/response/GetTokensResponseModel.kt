package com.example.argent.data.service.model.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class GetTokensResponseModel(
    val tokens: ArrayList<Tokens>? = arrayListOf()
) : Serializable {
    data class Tokens(

        @SerializedName("address") var address: String? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("decimals") var decimals: String? = null,
        @SerializedName("symbol") var symbol: String? = null,
        @SerializedName("totalSupply") var totalSupply: String? = null,
        @SerializedName("owner") var owner: String? = null,
        @SerializedName("txsCount") var txsCount: Int? = null,
        @SerializedName("transfersCount") var transfersCount: Int? = null,
        @SerializedName("lastUpdated") var lastUpdated: Int? = null,
        @SerializedName("slot") var slot: Int? = null,
        @SerializedName("issuancesCount") var issuancesCount: Int? = null,
        @SerializedName("holdersCount") var holdersCount: Int? = null,
        @SerializedName("image") var image: String? = null,
        @SerializedName("website") var website: String? = null,
        @SerializedName("coingecko") var coingecko: String? = null,
        @SerializedName("ethTransfersCount") var ethTransfersCount: Int? = null,
        @SerializedName("price") var price: Any? = null,
        @SerializedName("publicTags") var publicTags: ArrayList<String> = arrayListOf(),
        @SerializedName("opCount") var opCount: Int? = null

    )

    data class Price(

        @SerializedName("rate") val rate: Double?,
        @SerializedName("diff") val diff: Double?,
        @SerializedName("diff7d") val diff7d: Double?,
        @SerializedName("ts") val ts: Double?,
        @SerializedName("marketCapUsd") val marketCapUsd: Double?,
        @SerializedName("availableSupply") val availableSupply: Double?,
        @SerializedName("volume24h") val volume24h: Double?,
        @SerializedName("diff30d") val diff30d: Double?,
        @SerializedName("volDiff1") val volDiff1: Double?,
        @SerializedName("volDiff7") val volDiff7: Double?,
        @SerializedName("volDiff30") val volDiff30: Double?,
        @SerializedName("currency") val currency: String?

    )
}
