package com.study.movierecommend.domain.auth.service.impl

import com.study.movierecommend.domain.auth.domain.entity.RefreshToken
import com.study.movierecommend.domain.auth.domain.repository.RefreshTokenRepository
import com.study.movierecommend.domain.auth.presentation.data.res.RefreshResDto
import com.study.movierecommend.domain.auth.service.RefreshTokenService
import com.study.movierecommend.domain.user.domain.repository.UserRepository
import com.study.movierecommend.domain.user.enums.Role
import com.study.movierecommend.domain.user.exception.UserNotFoundException
import com.study.movierecommend.global.security.exception.TokenExpiredException
import com.study.movierecommend.global.security.exception.TokenInvalidException
import com.study.movierecommend.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
@Transactional
class RefreshTokenServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userRepository: UserRepository,
    private val tokenProvider: JwtTokenProvider
) : RefreshTokenService {

    override fun execute(refreshToken: String): RefreshResDto {
        val refresh = tokenProvider.parseToken(refreshToken)
            ?: throw TokenInvalidException()

        val email: String = tokenProvider.exactEmailFromRefreshToken(refresh)

        val user = userRepository.findByEmail(email)
            ?: throw UserNotFoundException()

        val role: Role = tokenProvider.exactRoleFromRefreshToken(refresh)

        val existingRefreshToken = refreshTokenRepository.findByToken(refresh)
            ?: throw TokenExpiredException()

        val newAccessToken = tokenProvider.generateAccessToken(email, role)
        val newRefreshToken = tokenProvider.generateRefreshToken(email, role)
        val accessExp: ZonedDateTime = tokenProvider.accessExpiredTime
        val refreshExp: ZonedDateTime = tokenProvider.refreshExpiredTime

        refreshTokenRepository.save(
            RefreshToken(
                userId = existingRefreshToken.userId,
                token = newRefreshToken
            )
        )

        return RefreshResDto(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken,
            accessExp = accessExp,
            refreshExp = refreshExp,
            roles = user.roles,
            expiresAt = accessExp
        )
    }
}