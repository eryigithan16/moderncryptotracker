package com.yeryigit.moderncryptotracker.di

import com.yeryigit.moderncryptotracker.core.data.networking.HttpClientFactory
import com.yeryigit.moderncryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.yeryigit.moderncryptotracker.crypto.domain.CoinDataSource
import com.yeryigit.moderncryptotracker.crypto.presentation.coin_list.components.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}