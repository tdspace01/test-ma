package com.example.movieapp.favourite.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.movieapp.favourite.presentation.FavouriteScreen
import com.example.movieapp.navigation.favourite.FavouriteRoute

fun EntryProviderScope<NavKey>.favouriteEntry() {
    entry<FavouriteRoute.Favourite> {
        FavouriteScreen()
    }
}