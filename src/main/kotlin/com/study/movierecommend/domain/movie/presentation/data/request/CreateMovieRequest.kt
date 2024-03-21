package com.study.movierecommend.domain.movie.presentation.data.request

import javax.validation.constraints.NotBlank

data class CreateMovieRequest(
    @field:NotBlank
    val title: String
)
