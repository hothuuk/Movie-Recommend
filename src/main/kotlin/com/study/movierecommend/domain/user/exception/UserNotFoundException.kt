package com.study.movierecommend.domain.user.exception

import com.study.movierecommend.global.error.ErrorCode
import com.study.movierecommend.global.error.exception.BasicException

class UserNotFoundException : BasicException(ErrorCode.USER_NOT_FOUND) {
}