package com.example.movieapp.home.presentation

import com.example.movieapp.domain.model.movie.PopularMovie

sealed interface HomeEvent {
    data object OnRefresh : HomeEvent
    data object OnClearSearch : HomeEvent
    data object OnGenreCleared : HomeEvent
    data object OnToggleGenresVisibility : HomeEvent
    data class OnGenreSelected(val genreId: Int) : HomeEvent
    data class OnSearchQueryChanged(val query: String) : HomeEvent
    data class OnToggleFavorite(val movie: PopularMovie) : HomeEvent
}