package com.yeryigit.moderncryptotracker.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveAsEvents(
    events: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle, key1, key2) {// lifecycle değiştiğinde tekrar trigger olur
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) { // lifecycle STARTED durumuna geldiğinde başlatılan, başka duruma geçince duraksayan bir yapıya girer
            withContext(Dispatchers.Main.immediate) { //scope içinde thread'i değiştirir, immediate durumu ise bu işlemin daha hızlı yapılmasını sağlar
                events.collect(onEvent) // bu da yolladığımız flow'ları dinlemek, işlemeye yarar.
            }
        }
    }
}