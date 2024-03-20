package com.study.movierecommend.domain.auth.domain.repository

import com.study.movierecommend.domain.auth.domain.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RefreshTokenRepository : CrudRepository<RefreshToken, UUID> {

    fun findByToken(token: String): RefreshToken?
}