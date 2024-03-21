package com.study.movierecommend.domain.movie.service.impl

import com.study.movierecommend.domain.movie.domain.entity.Movie
import com.study.movierecommend.domain.movie.domain.repository.MovieRepository
import com.study.movierecommend.domain.movie.exception.MovieAlreadyException
import com.study.movierecommend.domain.movie.presentation.data.dto.MovieDto
import com.study.movierecommend.domain.movie.service.CreateMovieService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateMovieServiceImpl(
    private val movieRepository: MovieRepository
) : CreateMovieService {

    override fun execute(movieDto: MovieDto) {

        if (movieRepository.existsByTitle(movieDto.title)) {
            throw MovieAlreadyException()
        }

        movieRepository.save(
            Movie(
                title = movieDto.title
            )
        )
    }
}