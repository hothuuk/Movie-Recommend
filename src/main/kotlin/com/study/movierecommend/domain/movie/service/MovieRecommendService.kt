package com.study.movierecommend.domain.movie.service

import com.study.movierecommend.domain.movie.presentation.data.response.MovieRecommendListResponse

interface MovieRecommendService {

    fun execute(): List<MovieRecommendListResponse>
}