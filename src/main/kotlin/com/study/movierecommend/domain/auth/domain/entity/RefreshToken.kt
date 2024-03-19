package com.study.movierecommend.domain.auth.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "refreshToken", timeToLive = 60L * 60 * 24 * 7)
class RefreshToken(
    @Indexed
    val userId: Long? = null,

    @Id
    @Indexed
    val token: String
)