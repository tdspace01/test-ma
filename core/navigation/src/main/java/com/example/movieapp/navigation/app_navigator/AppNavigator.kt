package com.example.movieapp.navigation.app_navigator

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation3.runtime.NavKey

interface AppNavigator {
    fun navigateTo(route: NavKey)
    fun clearAndNavigate(route: NavKey)
    fun navigateBack()
}

val LocalNavigator = staticCompositionLocalOf<AppNavigator> {
    error("No AppNavigator provided")
}