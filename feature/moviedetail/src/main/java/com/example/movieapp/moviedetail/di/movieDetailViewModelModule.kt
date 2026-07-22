package com.example.movieapp.moviedetail.di

import com.example.movieapp.moviedetail.presentation.MovieDetailViewModel
import com.example.movieapp.navigation.moviedetail.MovieDetailRoute
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val movieDetailViewModelModule = module {
    viewModel { (route: MovieDetailRoute.MovieDetail) ->
        MovieDetailViewModel(
            args = route,
            getMovieDetailsUseCase = get(),
            toggleFavoriteUseCase = get(),
            observeNetworkStatusUseCase = get()
        )
    }
}