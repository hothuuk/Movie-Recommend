package com.study.movierecommend.domain.auth.service

import com.study.movierecommend.domain.auth.presentation.data.dto.SignInDto
import com.study.movierecommend.domain.auth.presentation.data.response.SignInResDto

interface SignInService {
    fun execute(signInDto: SignInDto): SignInResDto
}