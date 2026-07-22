package com.example.movieapp.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movieapp.designsystem.design.MovieAppSizing
import com.example.movieapp.navigation.app_navigator.LocalNavigator
import com.example.movieapp.navigation.home.HomeRoute
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navigator = LocalNavigator.current

    LaunchedEffect(state.isTimerFinished) {
        if (state.isTimerFinished) {
            navigator.clearAndNavigate(HomeRoute.Home)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(com.example.movieapp.splash.R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier
                .width(MovieAppSizing.size80)
                .height(MovieAppSizing.size40)
        )
    }
}