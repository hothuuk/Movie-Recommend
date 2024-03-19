package com.study.movierecommend.domain.auth.service

import com.study.movierecommend.domain.auth.presentation.data.dto.SignUpDto

interface SignUpService {
    fun execute(signUpDto: SignUpDto)
}