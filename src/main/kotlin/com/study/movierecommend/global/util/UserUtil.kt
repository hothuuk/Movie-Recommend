package com.study.movierecommend.global.util

import com.study.movierecommend.domain.user.domain.entity.User
import com.study.movierecommend.domain.user.domain.repository.UserRepository
import com.study.movierecommend.domain.user.exception.UserNotFoundException
import com.study.movierecommend.global.security.auth.AuthDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserUtil(
    private val userRepository: UserRepository
) {
    fun currentUser(): User {
        val principal = SecurityContextHolder.getContext().authentication.principal
        val email = if (principal is AuthDetails) {
            principal.username
        } else {
            principal.toString()
        }

        return userRepository.findByEmail(email)
            ?: throw UserNotFoundException()
    }
}