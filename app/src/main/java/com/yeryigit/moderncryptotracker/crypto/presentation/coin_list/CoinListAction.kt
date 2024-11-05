package com.yeryigit.moderncryptotracker.crypto.presentation.coin_list

import com.yeryigit.moderncryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
}