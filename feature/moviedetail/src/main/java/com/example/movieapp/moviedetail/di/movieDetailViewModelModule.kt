package com.example.movieapp.moviedetail.di

import com.example.movieapp.moviedetail.presentation.MovieDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val movieDetailViewModelModule = module {
    viewModel { params ->
        MovieDetailViewModel(
            args = params.get(),
            getMovieDetailsUseCase = get(),
            toggleFavoriteUseCase = get(),
            observeNetworkStatusUseCase = get()
        )
    }
}