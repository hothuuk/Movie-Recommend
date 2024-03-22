package com.study.movierecommend.domain.user.service

import com.study.movierecommend.domain.user.presentation.data.response.MyHistoryListResponse

interface MyHistoryListService {

    fun execute(): List<MyHistoryListResponse>
}