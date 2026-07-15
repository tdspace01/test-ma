package com.example.movieapp.moviedetail.presentation

sealed interface MovieDetailEvent {
    data object OnRefresh : MovieDetailEvent
    data object OnToggleFavorite : MovieDetailEvent
}