package com.example.movieapp.splash.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.movieapp.navigation.splash.SplashRoute
import com.example.movieapp.splash.presentation.SplashScreen

fun EntryProviderScope<NavKey>.splashEntry() {
    entry<SplashRoute> {
        SplashScreen()
    }
}