package com.study.movierecommend.domain.user.service.impl

import com.study.movierecommend.domain.movie.domain.repository.UserMovieHistoryRepository
import com.study.movierecommend.domain.user.domain.entity.User
import com.study.movierecommend.domain.user.presentation.data.response.MyHistoryListResponse
import com.study.movierecommend.domain.user.service.MyHistoryListService
import com.study.movierecommend.global.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MyHistoryListServiceImpl(
    private val userUtil: UserUtil,
    private val userMovieHistoryRepository: UserMovieHistoryRepository
) : MyHistoryListService {

    override fun execute(): List<MyHistoryListResponse> {
        val user: User = userUtil.currentUser()

        return userMovieHistoryRepository.findMovieByUser(user)
            .map {
                MyHistoryListResponse(
                    id = it.id,
                    title = it.title
                )
            }
    }
}