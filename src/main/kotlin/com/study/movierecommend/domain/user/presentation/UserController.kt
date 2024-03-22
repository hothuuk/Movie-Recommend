package com.study.movierecommend.domain.user.presentation

import com.study.movierecommend.domain.user.presentation.data.response.MyHistoryListResponse
import com.study.movierecommend.domain.user.service.MyHistoryListService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/my")
class UserController(
    private val myHistoryListService: MyHistoryListService
) {
    @GetMapping("/history")
    fun myHistory(): ResponseEntity<List<MyHistoryListResponse>> =
        myHistoryListService.execute()
            .let { ResponseEntity.ok().body(it) }
}