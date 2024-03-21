package com.study.movierecommend.domain.movie.domain.entity

import javax.persistence.*

@Entity
@Table(name = "movie")
class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    val id: Long = 0,

    @Column(name = "movie_title", nullable = false, unique = true)
    val title: String
)
