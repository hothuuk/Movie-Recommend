package com.study.movierecommend.domain.user.domain.repository

import com.study.movierecommend.domain.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String?): User?
}