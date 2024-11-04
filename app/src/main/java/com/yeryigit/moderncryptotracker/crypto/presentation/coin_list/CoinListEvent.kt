package com.yeryigit.moderncryptotracker.crypto.presentation.coin_list

import com.yeryigit.moderncryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}