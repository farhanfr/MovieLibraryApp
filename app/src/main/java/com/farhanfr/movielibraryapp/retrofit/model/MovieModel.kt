package com.farhanfr.movielibraryapp.retrofit.model

data class MovieModel (
    var id: Int,
    var poster_path: String,
    var title: String,
    var overview: String,
    var vote_average: Double,
    var popularity: Double,
    var original_language: String
)