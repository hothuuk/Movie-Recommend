package com.study.movierecommend.domain.auth.presentation.data.request

import javax.validation.constraints.NotBlank

data class SignInRequest(
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String
)