package com.study.movierecommend.domain.movie.exception

import com.study.movierecommend.global.error.ErrorCode
import com.study.movierecommend.global.error.exception.BasicException

class MovieAlreadyException : BasicException(ErrorCode.MOVIE_ALREADY) {
}