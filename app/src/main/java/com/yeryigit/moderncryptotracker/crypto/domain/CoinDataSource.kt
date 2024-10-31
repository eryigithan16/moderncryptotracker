package com.yeryigit.moderncryptotracker.crypto.domain

import com.yeryigit.moderncryptotracker.core.domain.util.NetworkError
import com.yeryigit.moderncryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}