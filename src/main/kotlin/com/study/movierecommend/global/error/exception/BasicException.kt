package com.study.movierecommend.global.error.exception

import com.study.movierecommend.global.error.ErrorCode

open class BasicException(val errorCode: ErrorCode): RuntimeException()