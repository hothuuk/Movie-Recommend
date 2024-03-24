package com.study.movierecommend.domain.movie.domain.repository

import com.study.movierecommend.domain.movie.domain.entity.Movie
import com.study.movierecommend.domain.movie.domain.entity.UserMovieHistory
import com.study.movierecommend.domain.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserMovieHistoryRepository : JpaRepository<UserMovieHistory, Long> {

    fun existsByUserAndMovie(user: User, movie: Movie): Boolean

    @Query("select umh.movie from UserMovieHistory umh where umh.user = :user")
    fun findMovieByUser(@Param("user") user: User): List<Movie>

    fun findByUserId(userId: Long): List<UserMovieHistory>
}