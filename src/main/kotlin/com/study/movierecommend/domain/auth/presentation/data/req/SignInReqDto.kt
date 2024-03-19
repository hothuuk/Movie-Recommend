package com.study.movierecommend.domain.auth.presentation.data.req

import javax.validation.constraints.NotBlank

data class SignInReqDto(
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String
)