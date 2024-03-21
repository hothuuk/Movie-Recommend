package com.study.movierecommend.domain.auth.presentation.data.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.study.movierecommend.domain.user.enums.Role
import java.time.ZonedDateTime

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val accessExp: ZonedDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val refreshExp: ZonedDateTime,
    val roles: MutableList<Role>,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val expiresAt: ZonedDateTime
)