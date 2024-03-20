package com.study.movierecommend.domain.auth.service

import com.study.movierecommend.domain.auth.presentation.data.res.RefreshResDto

interface RefreshTokenService {
    fun execute(refreshToken: String): RefreshResDto
}