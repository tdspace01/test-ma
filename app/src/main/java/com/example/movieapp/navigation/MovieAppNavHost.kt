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
import com.example.movieapp.favourite.presentation.FavouriteScreen
import com.example.movieapp.home.presentation.HomeScreen
import com.example.movieapp.moviedetail.presentation.MovieDetailScreen
import com.example.movieapp.navigation.app_navigator.AppNavigator
import com.example.movieapp.navigation.app_navigator.LocalNavigator
import com.example.movieapp.navigation.favourite.FavouriteRoute
import com.example.movieapp.navigation.home.HomeRoute
import com.example.movieapp.navigation.moviedetail.MovieDetailRoute
import com.example.movieapp.navigation.splash.SplashRoute
import com.example.movieapp.splash.presentation.SplashScreen

@Composable
fun MovieAppNavHost(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(SplashRoute as NavKey)

    val navigator = remember(backStack) {
        object : AppNavigator {
            private var lastNavTime = 0L
            private val navDebounceMs = 400L
            override fun navigateTo(route: NavKey) {
                val currentTime = System.currentTimeMillis()

                if (currentTime - lastNavTime >= navDebounceMs) {
                    if (backStack.lastOrNull() != route) {
                        lastNavTime = currentTime
                        backStack.add(route)
                    }
                }
            }

            override fun navigateBack() {
                val currentTime = System.currentTimeMillis()

                if (currentTime - lastNavTime >= navDebounceMs) {
                    if (backStack.size > 1) {
                        lastNavTime = currentTime
                        backStack.removeLastOrNull()
                    }
                }
            }

            override fun replaceRoot(route: NavKey) {
                lastNavTime = System.currentTimeMillis()
                backStack.clear()
                backStack.add(route)
            }
        }
    }

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
                entry<SplashRoute> { SplashScreen() }
                entry<HomeRoute.Home> { HomeScreen() }
                entry<FavouriteRoute.Favourite> { FavouriteScreen() }
                entry<MovieDetailRoute.MovieDetail> { key -> MovieDetailScreen(route = key) }
            }
        )
    }
}