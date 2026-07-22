package com.example.movieapp.splash.presentation

import androidx.lifecycle.viewModelScope
import com.example.movieapp.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class SplashViewModel : BaseViewModel<SplashState, Unit, Unit>(
    initialState = SplashState()
) {

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            delay(1000.milliseconds)
            updateState { copy(isTimerFinished = true) }
        }
    }
}