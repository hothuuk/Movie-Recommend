package com.study.movierecommend.domain.user.domain.entity

import com.study.movierecommend.domain.user.enums.Role
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long = 0,

    @Column(name = "user_email", nullable = false, unique = true)
    val email: String,

    @Column(name = "user_password", nullable = false)
    val password: String,

    @Column(name = "user_name", nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = [JoinColumn(name = "user_id")])
    var roles: MutableList<Role>
)