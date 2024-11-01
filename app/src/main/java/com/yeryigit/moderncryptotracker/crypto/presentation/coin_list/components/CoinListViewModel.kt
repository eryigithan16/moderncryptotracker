package com.yeryigit.moderncryptotracker.crypto.presentation.coin_list.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeryigit.moderncryptotracker.core.domain.util.onError
import com.yeryigit.moderncryptotracker.core.domain.util.onSuccess
import com.yeryigit.moderncryptotracker.crypto.domain.CoinDataSource
import com.yeryigit.moderncryptotracker.crypto.presentation.coin_list.CoinListState
import com.yeryigit.moderncryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(private val coinDataSource: CoinDataSource) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {

            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            coinDataSource.getCoins()
                .onSuccess { coins ->
                    _state.update { it.copy(isLoading = false, coins = coins.map { it.toCoinUi() }) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}