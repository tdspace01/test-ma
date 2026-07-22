package com.example.movieapp.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.movieapp.home.presentation.HomeScreen
import com.example.movieapp.navigation.home.HomeRoute

fun EntryProviderScope<NavKey>.homeEntry() {
    entry<HomeRoute.Home> {
        HomeScreen()
    }
}