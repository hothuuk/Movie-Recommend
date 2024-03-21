package com.study.movierecommend.domain.movie.presentation

import com.study.movierecommend.domain.movie.presentation.data.request.CreateMovieRequest
import com.study.movierecommend.domain.movie.service.CreateMovieService
import com.study.movierecommend.domain.movie.service.WatchedMovieService
import com.study.movierecommend.domain.movie.util.MovieConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/movie")
class MovieController(
    private val createMovieService: CreateMovieService,
    private val watchedMovieService: WatchedMovieService,
    private val movieConverter: MovieConverter
) {

    @PostMapping
    fun create(@Valid @RequestBody createMovieRequest: CreateMovieRequest) : ResponseEntity<Void> =
        movieConverter.toDto(createMovieRequest)
            .let { createMovieService.execute(it) }
            .run { ResponseEntity.ok().build() }

    @PostMapping("/watch/{movie_id}")
    fun watchedMovie(@PathVariable("movie_id") movieId: Long) : ResponseEntity<Void> =
        watchedMovieService.execute(movieId)
            .run { ResponseEntity.ok().build() }
}