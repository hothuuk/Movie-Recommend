package com.study.movierecommend.domain.movie.domain.entity

import com.study.movierecommend.domain.user.domain.entity.User
import javax.persistence.*

@Entity
@Table(name = "user_movie_history")
class UserMovieHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_movie_history_id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "movie_id")
    val movie: Movie
)
