package com.example.movieapp.moviedetail.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.movieapp.moviedetail.presentation.MovieDetailScreen
import com.example.movieapp.navigation.moviedetail.MovieDetailRoute

fun EntryProviderScope<NavKey>.movieDetailEntry() {
    entry<MovieDetailRoute.MovieDetail> { key ->
        MovieDetailScreen(route = key)
    }
}