package com.study.movierecommend.domain.auth.service

import com.study.movierecommend.domain.auth.presentation.data.response.RefreshResDto

interface RefreshTokenService {
    fun execute(refreshToken: String): RefreshResDto
}