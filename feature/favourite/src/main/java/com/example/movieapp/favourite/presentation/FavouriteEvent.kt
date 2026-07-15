package com.example.movieapp.favourite.presentation

import com.example.movieapp.domain.model.movie.PopularMovie

sealed interface FavouriteEvent {
    data object ObserveFavorites : FavouriteEvent
    data class OnRemoveFavorite(val movie: PopularMovie) : FavouriteEvent
}