package com.study.movierecommend.domain.auth.exception

import com.study.movierecommend.global.error.ErrorCode
import com.study.movierecommend.global.error.exception.BasicException

class RoleNotExistException : BasicException(ErrorCode.ROLE_NOT_EXIST) {
}