package com.study.movierecommend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieRecommendApplication

fun main(args: Array<String>) {
	runApplication<MovieRecommendApplication>(*args)
}
