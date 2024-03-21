package com.study.movierecommend.domain.auth.util

import com.study.movierecommend.domain.auth.presentation.data.dto.SignInDto
import com.study.movierecommend.domain.auth.presentation.data.dto.SignUpDto
import com.study.movierecommend.domain.auth.presentation.data.request.SignInRequest
import com.study.movierecommend.domain.auth.presentation.data.request.SignUpRequest

interface AuthConverter {

    fun toDto(signUpRequest: SignUpRequest): SignUpDto

    fun toDto(signInRequest: SignInRequest): SignInDto
}