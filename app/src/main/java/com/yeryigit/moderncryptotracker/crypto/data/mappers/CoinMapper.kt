package com.yeryigit.moderncryptotracker.crypto.data.mappers

import com.yeryigit.moderncryptotracker.crypto.data.networking.dto.CoinDto
import com.yeryigit.moderncryptotracker.crypto.domain.Coin

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