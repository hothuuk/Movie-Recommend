package com.study.movierecommend.domain.movie.util

import com.study.movierecommend.domain.movie.presentation.data.dto.MovieDto
import com.study.movierecommend.domain.movie.presentation.data.request.CreateMovieRequest

interface MovieConverter {

    fun toDto(createMovieRequest: CreateMovieRequest): MovieDto
}