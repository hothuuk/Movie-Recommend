package com.study.movierecommend.domain.movie.service.impl

import com.study.movierecommend.domain.movie.domain.entity.UserMovieHistory
import com.study.movierecommend.domain.movie.domain.repository.MovieRepository
import com.study.movierecommend.domain.movie.domain.repository.UserMovieHistoryRepository
import com.study.movierecommend.domain.movie.presentation.data.response.MovieRecommendListResponse
import com.study.movierecommend.domain.movie.service.MovieRecommendService
import com.study.movierecommend.domain.user.domain.entity.User
import com.study.movierecommend.global.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MovieRecommendServiceImpl(
    private val userUtil: UserUtil,
    private val userMovieHistoryRepository: UserMovieHistoryRepository,
    private val movieRepository: MovieRepository
) : MovieRecommendService {

    override fun execute(): List<MovieRecommendListResponse> {

        val user: User = userUtil.currentUser()

        val userHistory = userMovieHistoryRepository.findByUserId(user.id)
        val allUserHistory = userMovieHistoryRepository.findAll()

        val similarityScores = mutableMapOf<Long, Double>()
        for (history in allUserHistory) {
            if (history.user.id != user.id) {
                val similarity = calculateSimilarity(userHistory, listOf(history))
                similarityScores[history.user.id] = similarity
            }
        }

        val topSimilarUsers = similarityScores.toList().sortedByDescending { (_, value) -> value }.take(10)

        val recommendedMovies = mutableMapOf<Long, Double>()
        for ((otherUserId, similarity) in topSimilarUsers) {
            val otherUserHistory = userMovieHistoryRepository.findByUserId(otherUserId)
            for (history in otherUserHistory) {
                if (!userHistory.any { it.movie.id == history.movie.id }) {
                    recommendedMovies[history.movie.id] = recommendedMovies.getOrDefault(history.movie.id, 0.0) + similarity
                }
            }
        }

        val topRecommendedMovies = recommendedMovies.toList().sortedByDescending { (_, value) -> value }.take(10)

        return topRecommendedMovies
            .map {
                MovieRecommendListResponse(
                    id = it.first,
                    title = movieRepository.findById(it.first).get().title
                )
            }
    }

    private fun calculateSimilarity(userHistory: List<UserMovieHistory>, otherHistory: List<UserMovieHistory>): Double {
        val userSet = userHistory.map { it.movie.id }.toSet()
        val otherSet = otherHistory.map { it.movie.id }.toSet()
        val intersection = userSet.intersect(otherSet).size
        val union = userSet.size + otherSet.size - intersection
        return intersection.toDouble() / union.toDouble()
    }
}