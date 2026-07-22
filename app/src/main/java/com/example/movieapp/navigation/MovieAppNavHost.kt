package com.example.movieapp.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.movieapp.favourite.navigation.favouriteEntry
import com.example.movieapp.home.navigation.homeEntry
import com.example.movieapp.moviedetail.navigation.movieDetailEntry
import com.example.movieapp.navigation.app_navigator.DefaultAppNavigator
import com.example.movieapp.navigation.app_navigator.LocalNavigator
import com.example.movieapp.navigation.splash.SplashRoute
import com.example.movieapp.splash.navigation.splashEntry

@Composable
fun MovieAppNavHost(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(SplashRoute as NavKey)
    val navigator = remember(backStack) { DefaultAppNavigator(backStack) }

    CompositionLocalProvider(LocalNavigator provides navigator) {
        NavDisplay(
            backStack = backStack,
            modifier = modifier,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            onBack = { navigator.navigateBack() },
            transitionSpec = {
                slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
            },
            popTransitionSpec = {
                slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
            },
            predictivePopTransitionSpec = {
                slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
            },
            entryProvider = entryProvider {
                homeEntry()
                splashEntry()
                favouriteEntry()
                movieDetailEntry()
            }
        )
    }
}