package com.example.movieapp.navigation.favourite

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class FavouriteRoute : NavKey {
    @Serializable
    data object Favourite : FavouriteRoute()
}