package com.example.movieapp.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.movieapp.favourite.presentation.FavouriteScreen
import com.example.movieapp.home.presentation.HomeScreen
import com.example.movieapp.moviedetail.presentation.MovieDetailScreen
import com.example.movieapp.navigation.favourite.FavouriteRoute
import com.example.movieapp.navigation.home.HomeRoute
import com.example.movieapp.navigation.moviedetail.MovieDetailRoute
import com.example.movieapp.navigation.splash.SplashRoute
import com.example.movieapp.splash.presentation.SplashScreen
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator

@Composable
fun MovieAppNavHost(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(SplashRoute as NavKey)

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },
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

            entry<SplashRoute> {
                SplashScreen(
                    onNavigateToHome = {
                        backStack.clear()
                        backStack.add(HomeRoute.Home)
                    }
                )
            }

            entry<HomeRoute.Home> {
                HomeScreen(
                    onNavigateToDetail = { id, categoryText ->
                        backStack.add(
                            MovieDetailRoute.MovieDetail(movieId = id, category = categoryText)
                        )
                    },
                    onNavigateToFavorite = {
                        if (backStack.lastOrNull() != FavouriteRoute.Favourite) {
                            backStack.add(FavouriteRoute.Favourite)
                        }
                    }
                )
            }

            entry<FavouriteRoute.Favourite> {
                FavouriteScreen(
                    onNavigateToDetails = { id, categoryText ->
                        backStack.add(
                            MovieDetailRoute.MovieDetail(movieId = id, category = categoryText)
                        )
                    },
                    onNavigateToHome = {
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    }
                )
            }

            entry<MovieDetailRoute.MovieDetail> { key ->
                MovieDetailScreen(
                    route = key,
                    onNavigateBack = {
                        if (backStack.lastOrNull() is MovieDetailRoute.MovieDetail) {
                            backStack.removeLastOrNull()
                        }
                    }
                )
            }
        }
    )
}