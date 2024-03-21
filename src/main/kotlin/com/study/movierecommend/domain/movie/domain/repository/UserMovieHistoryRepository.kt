package com.study.movierecommend.domain.movie.domain.repository

import com.study.movierecommend.domain.movie.domain.entity.Movie
import com.study.movierecommend.domain.movie.domain.entity.UserMovieHistory
import com.study.movierecommend.domain.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserMovieHistoryRepository : JpaRepository<UserMovieHistory, Long> {

    fun existsByUserAndMovie(user: User, movie: Movie): Boolean
}