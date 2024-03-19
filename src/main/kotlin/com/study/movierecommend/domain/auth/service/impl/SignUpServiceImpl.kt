package com.study.movierecommend.domain.auth.service.impl

import com.study.movierecommend.domain.auth.presentation.data.dto.SignUpDto
import com.study.movierecommend.domain.auth.service.SignUpService
import com.study.movierecommend.domain.user.domain.entity.User
import com.study.movierecommend.domain.user.domain.repository.UserRepository
import com.study.movierecommend.domain.user.enums.Role
import com.study.movierecommend.domain.user.exception.EmailAlreadyException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignUpServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : SignUpService {
    override fun execute(signUpDto: SignUpDto) {

        if (userRepository.existsByEmail(signUpDto.email)) {
            throw EmailAlreadyException()
        }

        val encodedPassword = encode(passwordEncoder, signUpDto.password)

        userRepository.save(toEntity(signUpDto, encodedPassword))
    }

    private fun encode(passwordEncoder: PasswordEncoder, password: String): String =
        passwordEncoder.encode(password)

    private fun toEntity(signUpDto: SignUpDto, encodedPassword: String): User =
        User(
            email = signUpDto.email,
            password = encodedPassword,
            name = signUpDto.name,
            roles = Collections.singletonList(Role.ROLE_MEMBER)
        )
}