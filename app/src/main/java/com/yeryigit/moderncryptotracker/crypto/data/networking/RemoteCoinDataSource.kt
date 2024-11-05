package com.yeryigit.moderncryptotracker.crypto.data.networking

import com.yeryigit.moderncryptotracker.core.data.networking.constructUrl
import com.yeryigit.moderncryptotracker.core.data.networking.safeCall
import com.yeryigit.moderncryptotracker.core.domain.util.NetworkError
import com.yeryigit.moderncryptotracker.core.domain.util.Result
import com.yeryigit.moderncryptotracker.core.domain.util.map
import com.yeryigit.moderncryptotracker.crypto.data.mappers.toCoin
import com.yeryigit.moderncryptotracker.crypto.data.mappers.toCoinPrice
import com.yeryigit.moderncryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.yeryigit.moderncryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.yeryigit.moderncryptotracker.crypto.domain.Coin
import com.yeryigit.moderncryptotracker.crypto.domain.CoinDataSource
import com.yeryigit.moderncryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(private val httpClient: HttpClient) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(urlString = constructUrl("/assets"))
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(coinId: String, start: ZonedDateTime, end: ZonedDateTime): Result<List<CoinPrice>, NetworkError> {
        val startMilLis = start.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMilLis = end.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        return safeCall<CoinHistoryDto> {
            httpClient.get(urlString = constructUrl("/assets/$coinId/history")) {
                parameter("interval", "h6")
                parameter("start", startMilLis)
                parameter("end", endMilLis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}