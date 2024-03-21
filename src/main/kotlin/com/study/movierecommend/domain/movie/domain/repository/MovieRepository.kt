package com.study.movierecommend.domain.movie.domain.repository

import com.study.movierecommend.domain.movie.domain.entity.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<Movie, Long> {

    fun existsByTitle(title: String): Boolean
}