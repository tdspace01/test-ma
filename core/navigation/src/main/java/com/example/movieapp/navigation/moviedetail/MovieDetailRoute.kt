package com.example.movieapp.navigation.moviedetail

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface MovieDetailRoute : NavKey {
    @Serializable
    data class MovieDetail(
        val movieId: Int,
        val category: String
    ) : MovieDetailRoute
}