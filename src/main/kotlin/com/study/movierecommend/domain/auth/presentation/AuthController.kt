package com.study.movierecommend.domain.auth.presentation

import com.study.movierecommend.domain.auth.presentation.data.request.SignInRequest
import com.study.movierecommend.domain.auth.presentation.data.request.SignUpRequest
import com.study.movierecommend.domain.auth.presentation.data.response.RefreshResponse
import com.study.movierecommend.domain.auth.presentation.data.response.SignInResponse
import com.study.movierecommend.domain.auth.service.LogoutService
import com.study.movierecommend.domain.auth.service.RefreshTokenService
import com.study.movierecommend.domain.auth.service.SignInService
import com.study.movierecommend.domain.auth.service.SignUpService
import com.study.movierecommend.domain.auth.util.AuthConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val refreshTokenService: RefreshTokenService,
    private val logoutService: LogoutService,
    private val authConverter: AuthConverter
) {
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<Void> =
        authConverter.toDto(signUpRequest)
            .let { signUpService.execute(it) }
            .run { ResponseEntity.ok().build() }

    @PostMapping("/signIn")
    fun signIn(@Valid @RequestBody signInRequest: SignInRequest): ResponseEntity<SignInResponse> =
        authConverter.toDto(signInRequest)
            .let { ResponseEntity.ok(signInService.execute(it)) }

    @PatchMapping("/reissue")
    fun reissue(@RequestHeader("Refresh-Token") refreshToken: String): ResponseEntity<RefreshResponse> =
        ResponseEntity.ok().body(refreshTokenService.execute(refreshToken))

    @DeleteMapping("/logout")
    fun logout(): ResponseEntity<Void> =
        logoutService.execute()
            .let { ResponseEntity.noContent().build() }
}