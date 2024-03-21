package com.study.movierecommend.domain.auth.service

import com.study.movierecommend.domain.auth.presentation.data.response.RefreshResponse

interface RefreshTokenService {
    fun execute(refreshToken: String): RefreshResponse
}