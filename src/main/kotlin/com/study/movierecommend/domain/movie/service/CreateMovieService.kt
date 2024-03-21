package com.study.movierecommend.domain.movie.service

import com.study.movierecommend.domain.movie.presentation.data.dto.MovieDto

interface CreateMovieService {

    fun execute(movieDto: MovieDto)
}