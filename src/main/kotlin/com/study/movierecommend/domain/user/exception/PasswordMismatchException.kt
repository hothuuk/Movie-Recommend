package com.study.movierecommend.domain.user.exception

import com.study.movierecommend.global.error.ErrorCode
import com.study.movierecommend.global.error.exception.BasicException

class PasswordMismatchException : BasicException(ErrorCode.PASSWORD_MISMATCH) {
}