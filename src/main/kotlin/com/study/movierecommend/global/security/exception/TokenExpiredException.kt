package com.study.movierecommend.global.security.exception

import com.study.movierecommend.global.error.ErrorCode
import com.study.movierecommend.global.error.exception.BasicException

class TokenExpiredException : BasicException(ErrorCode.TOKEN_EXPIRED) {
}