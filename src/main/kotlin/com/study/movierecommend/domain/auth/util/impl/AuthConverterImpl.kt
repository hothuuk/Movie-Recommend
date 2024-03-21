package com.study.movierecommend.domain.auth.util.impl

import com.study.movierecommend.domain.auth.presentation.data.dto.SignInDto
import com.study.movierecommend.domain.auth.presentation.data.dto.SignUpDto
import com.study.movierecommend.domain.auth.presentation.data.request.SignInReqDto
import com.study.movierecommend.domain.auth.presentation.data.request.SignUpReqDto
import com.study.movierecommend.domain.auth.util.AuthConverter
import org.springframework.stereotype.Component

@Component
class AuthConverterImpl : AuthConverter {

    override fun toDto(signUpReqDto: SignUpReqDto): SignUpDto =
        SignUpDto(
            email = signUpReqDto.email,
            password = signUpReqDto.password,
            name = signUpReqDto.name
        )

    override fun toDto(signInReqDto: SignInReqDto): SignInDto =
        SignInDto(
            email = signInReqDto.email,
            password = signInReqDto.password
        )
}