package com.example.movieapp.splash.presentation

import androidx.lifecycle.viewModelScope
import com.example.movieapp.navigation.app_navigator.AppNavigator
import com.example.movieapp.navigation.home.HomeRoute
import com.example.movieapp.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class SplashViewModel : BaseViewModel<Unit, Unit, Unit>(Unit) {

    fun startTimer(navigator: AppNavigator) {
        viewModelScope.launch {
            delay(2000.milliseconds)
            navigator.replaceRoot(HomeRoute.Home)
        }
    }
}