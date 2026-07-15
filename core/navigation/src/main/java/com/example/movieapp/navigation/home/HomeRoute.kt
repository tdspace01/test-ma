package com.example.movieapp.navigation.home

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class HomeRoute : NavKey {
    @Serializable
    data object Home : HomeRoute()
}