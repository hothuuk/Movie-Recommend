package com.study.movierecommend.domain.movie.service.impl

import com.study.movierecommend.domain.movie.domain.entity.Movie
import com.study.movierecommend.domain.movie.domain.entity.UserMovieHistory
import com.study.movierecommend.domain.movie.domain.repository.MovieRepository
import com.study.movierecommend.domain.movie.domain.repository.UserMovieHistoryRepository
import com.study.movierecommend.domain.movie.exception.MovieAlreadyWatchedException
import com.study.movierecommend.domain.movie.exception.MovieNotFoundException
import com.study.movierecommend.domain.movie.service.WatchedMovieService
import com.study.movierecommend.domain.user.domain.entity.User
import com.study.movierecommend.global.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WatchedMovieServiceImpl(
    private val userMovieHistoryRepository: UserMovieHistoryRepository,
    private val movieRepository: MovieRepository,
    private val userUtil: UserUtil
) : WatchedMovieService {

    override fun execute(movieId: Long) {

        val movie: Movie = movieRepository.findById(movieId)
            .orElseThrow { MovieNotFoundException() }

        val user: User = userUtil.currentUser()

        if (userMovieHistoryRepository.existsByUserAndMovie(user, movie)) {
            throw MovieAlreadyWatchedException()
        }

        userMovieHistoryRepository.save(
            UserMovieHistory(
                user = user,
                movie = movie
            )
        )
    }
}