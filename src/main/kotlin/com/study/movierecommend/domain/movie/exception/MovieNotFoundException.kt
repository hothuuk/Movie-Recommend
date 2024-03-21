package com.study.movierecommend.domain.movie.exception

import com.study.movierecommend.global.error.ErrorCode
import com.study.movierecommend.global.error.exception.BasicException

class MovieNotFoundException : BasicException(ErrorCode.MOVIE_NOT_FOUND) {
}