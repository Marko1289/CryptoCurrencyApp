package com.example.cryptocurrencyapp.models

import android.os.Parcelable
import java.io.Serializable


data class CryptoCurrencyModel(


    val ImageUrl: String?,
    val ContentCreatedOn: String?,
    val Name: String?,
    val Symbol: String?,
    val CoinName: String?,
    val FullName: String?,
    val Description: String?,
    val AssetTokenStatus: String?,
    val Algorithm: String?,
    val ProofType: String?,
    val SortOrder: String?,
    val Sponsored: Boolean?,
    val IsTrading: Boolean?,
    val TotalCoinsMined: Double?,
    val BlockNumber: Int?,
    val NetHashesPerSecond: Double?,
    val BlockReward: Double?,
    val BlockTime: Double?,
    val AssetLaunchDate: String?,
    val MaxSupply: Double?,
    val MktCapPenalty: Double?,
    val IsUsedInDefi: Int?,
    val IsUsedInNft: Int?
) : Serializable