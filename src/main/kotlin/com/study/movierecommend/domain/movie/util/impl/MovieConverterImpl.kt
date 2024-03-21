package com.study.movierecommend.domain.movie.util.impl

import com.study.movierecommend.domain.movie.presentation.data.dto.MovieDto
import com.study.movierecommend.domain.movie.presentation.data.request.CreateMovieRequest
import com.study.movierecommend.domain.movie.util.MovieConverter
import org.springframework.stereotype.Component

@Component
class MovieConverterImpl : MovieConverter {

    override fun toDto(createMovieRequest: CreateMovieRequest): MovieDto =
        MovieDto(
            title = createMovieRequest.title
        )
}