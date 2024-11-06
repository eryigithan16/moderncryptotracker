package com.yeryigit.moderncryptotracker.crypto.data.mappers

import com.yeryigit.moderncryptotracker.crypto.data.networking.dto.CoinDto
import com.yeryigit.moderncryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.yeryigit.moderncryptotracker.crypto.domain.Coin
import com.yeryigit.moderncryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = this.priceUsd,
        dateTime = Instant.ofEpochMilli(this.time).atZone(ZoneId.systemDefault())
    )
}