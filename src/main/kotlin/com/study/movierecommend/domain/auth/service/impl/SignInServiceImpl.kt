package com.study.movierecommend.domain.auth.service.impl

import com.study.movierecommend.domain.auth.domain.entity.RefreshToken
import com.study.movierecommend.domain.auth.domain.repository.RefreshTokenRepository
import com.study.movierecommend.domain.auth.presentation.data.dto.SignInDto
import com.study.movierecommend.domain.auth.presentation.data.response.SignInResponse
import com.study.movierecommend.domain.auth.service.SignInService
import com.study.movierecommend.domain.user.domain.repository.UserRepository
import com.study.movierecommend.domain.user.enums.Role
import com.study.movierecommend.domain.user.exception.PasswordMismatchException
import com.study.movierecommend.domain.user.exception.UserNotFoundException
import com.study.movierecommend.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
@Transactional
class SignInServiceImpl(
    private val userRepository: UserRepository,
    private val tokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenRepository: RefreshTokenRepository
) : SignInService {
    override fun execute(signInDto: SignInDto): SignInResponse {

        val user = userRepository.findByEmail(signInDto.email)
            ?: throw UserNotFoundException()

        if (!passwordEncoder.matches(signInDto.password, user.password)) {
            throw PasswordMismatchException()
        }

        val accessToken = tokenProvider.generateAccessToken(signInDto.email, role = user.roles.first())
        val accessExp = tokenProvider.accessExpiredTime
        val expiresAt = tokenProvider.accessExpiredTime
        val refreshToken = tokenProvider.generateRefreshToken(signInDto.email, role = user.roles.first())
        val refreshExp = tokenProvider.refreshExpiredTime

        refreshTokenRepository.save(
            RefreshToken(
                userId = user.id,
                token = refreshToken
            )
        )

        return toResponse(
            accessToken,
            refreshToken,
            accessExp,
            refreshExp,
            user.roles,
            expiresAt
        )
    }

    private fun toResponse(
        accessToken: String,
        refreshToken: String,
        accessExp: ZonedDateTime,
        refreshExp: ZonedDateTime,
        roles: MutableList<Role>,
        expiresAt: ZonedDateTime
    ): SignInResponse =
        SignInResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessExp = accessExp,
            refreshExp = refreshExp,
            roles = roles,
            expiresAt = expiresAt
        )
}