package com.yeryigit.moderncryptotracker.crypto.presentation.coin_list.components

import com.yeryigit.moderncryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
}