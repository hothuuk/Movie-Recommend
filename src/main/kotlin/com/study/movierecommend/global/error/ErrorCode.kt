package com.study.movierecommend.global.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val message: String,
    val status: Int,
) {
    // SERVER ERROR
    UNKNOWN_ERROR("알 수 없는 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    // TOKEN
    TOKEN_INVALID("변질된 토큰입니다.", HttpStatus.FORBIDDEN.value()),
    TOKEN_EXPIRED("토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED.value()),

    // AUTH
    ROLE_NOT_EXIST("역할이 존재하지 않습니다.", HttpStatus.NOT_FOUND.value()),

    // USER
    USER_NOT_FOUND("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND.value()),
    EMAIL_ALREADY("존재하는 이메일입니다.", HttpStatus.CONFLICT.value()),
    PASSWORD_MISMATCH("비밀번호가 맞지 않습니다.", HttpStatus.BAD_REQUEST.value()),

    // MOVIE
    MOVIE_ALREADY("존재하는 영화입니다.", HttpStatus.CONFLICT.value())
}