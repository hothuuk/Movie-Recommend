package com.study.movierecommend.domain.auth.presentation

import com.study.movierecommend.domain.auth.presentation.data.req.SignInReqDto
import com.study.movierecommend.domain.auth.presentation.data.req.SignUpReqDto
import com.study.movierecommend.domain.auth.presentation.data.res.SignInResDto
import com.study.movierecommend.domain.auth.service.SignInService
import com.study.movierecommend.domain.auth.service.SignUpService
import com.study.movierecommend.domain.auth.util.AuthConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val authConverter: AuthConverter
) {
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody signUpReqDto: SignUpReqDto): ResponseEntity<Void> =
        authConverter.toDto(signUpReqDto)
            .let { signUpService.execute(it) }
            .run { ResponseEntity.ok().build() }

    @PostMapping("/signIn")
    fun signIn(@Valid @RequestBody signInReqDto: SignInReqDto): ResponseEntity<SignInResDto> =
        authConverter.toDto(signInReqDto)
            .let { ResponseEntity.ok(signInService.execute(it)) }
}