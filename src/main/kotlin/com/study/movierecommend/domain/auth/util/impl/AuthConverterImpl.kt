package com.study.movierecommend.domain.auth.util.impl

import com.study.movierecommend.domain.auth.presentation.data.dto.SignInDto
import com.study.movierecommend.domain.auth.presentation.data.dto.SignUpDto
import com.study.movierecommend.domain.auth.presentation.data.request.SignInRequest
import com.study.movierecommend.domain.auth.presentation.data.request.SignUpRequest
import com.study.movierecommend.domain.auth.util.AuthConverter
import org.springframework.stereotype.Component

@Component
class AuthConverterImpl : AuthConverter {

    override fun toDto(signUpRequest: SignUpRequest): SignUpDto =
        SignUpDto(
            email = signUpRequest.email,
            password = signUpRequest.password,
            name = signUpRequest.name
        )

    override fun toDto(signInRequest: SignInRequest): SignInDto =
        SignInDto(
            email = signInRequest.email,
            password = signInRequest.password
        )
}