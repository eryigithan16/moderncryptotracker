package com.yeryigit.moderncryptotracker.crypto.data.networking

import com.yeryigit.moderncryptotracker.core.data.networking.constructUrl
import com.yeryigit.moderncryptotracker.core.data.networking.safeCall
import com.yeryigit.moderncryptotracker.core.domain.util.NetworkError
import com.yeryigit.moderncryptotracker.core.domain.util.Result
import com.yeryigit.moderncryptotracker.core.domain.util.map
import com.yeryigit.moderncryptotracker.crypto.data.mappers.toCoin
import com.yeryigit.moderncryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.yeryigit.moderncryptotracker.crypto.domain.Coin
import com.yeryigit.moderncryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(private val httpClient: HttpClient) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(urlString = constructUrl("/assets"))
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}