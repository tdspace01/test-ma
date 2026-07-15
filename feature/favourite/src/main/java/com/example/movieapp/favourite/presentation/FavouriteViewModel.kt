package com.example.movieapp.favourite.presentation

import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.movie.PopularMovie
import com.example.movieapp.domain.usecase.movie.GetFavouriteMoviesUseCase
import com.example.movieapp.domain.usecase.movie.ToggleFavouriteUseCase
import com.example.movieapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val getFavoriteMoviesUseCase: GetFavouriteMoviesUseCase,
    private val toggleFavoriteUseCase: ToggleFavouriteUseCase
) : BaseViewModel<FavouriteState, FavouriteEvent, Unit>(FavouriteState()) {

    init {
        onEvent(FavouriteEvent.ObserveFavorites)
    }

    override fun onEvent(event: FavouriteEvent) {
        when (event) {
            is FavouriteEvent.ObserveFavorites -> observeFavorites()
            is FavouriteEvent.OnRemoveFavorite -> handleRemoveFavorite(event.movie)
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            getFavoriteMoviesUseCase().collect { moviesList ->
                updateState { copy(favoriteMovies = moviesList) }
            }
        }
    }

    private fun handleRemoveFavorite(movie: PopularMovie) {
        viewModelScope.launch {
            toggleFavoriteUseCase(movie)
        }
    }
}