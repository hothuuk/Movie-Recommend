package com.study.movierecommend.domain.auth.service.impl

import com.study.movierecommend.domain.auth.domain.entity.RefreshToken
import com.study.movierecommend.domain.auth.domain.repository.RefreshTokenRepository
import com.study.movierecommend.domain.auth.service.LogoutService
import com.study.movierecommend.domain.user.domain.entity.User
import com.study.movierecommend.domain.user.exception.UserNotFoundException
import com.study.movierecommend.global.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LogoutServiceImpl(
    private val userUtil: UserUtil,
    private val refreshTokenRepository: RefreshTokenRepository
) : LogoutService {
    override fun execute() {
        val user: User = userUtil.currentUser()

        val refreshToken: RefreshToken = refreshTokenRepository.findByUserId(user.id)
            ?: throw UserNotFoundException()

        refreshTokenRepository.delete(refreshToken)
    }
}