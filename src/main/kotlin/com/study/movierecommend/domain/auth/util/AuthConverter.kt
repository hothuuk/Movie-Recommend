package com.study.movierecommend.domain.auth.util

import com.study.movierecommend.domain.auth.presentation.data.dto.SignInDto
import com.study.movierecommend.domain.auth.presentation.data.dto.SignUpDto
import com.study.movierecommend.domain.auth.presentation.data.req.SignInReqDto
import com.study.movierecommend.domain.auth.presentation.data.req.SignUpReqDto

interface AuthConverter {

    fun toDto(signUpReqDto: SignUpReqDto): SignUpDto

    fun toDto(signInReqDto: SignInReqDto): SignInDto
}