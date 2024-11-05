package com.yeryigit.moderncryptotracker.crypto.domain

import com.yeryigit.moderncryptotracker.core.domain.util.NetworkError
import com.yeryigit.moderncryptotracker.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(coinId: String, start: ZonedDateTime, end: ZonedDateTime): Result<List<CoinPrice>, NetworkError>
}