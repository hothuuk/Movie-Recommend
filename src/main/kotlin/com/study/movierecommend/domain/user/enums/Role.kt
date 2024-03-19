package com.study.movierecommend.domain.user.enums

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority {
    ROLE_ADMIN, ROLE_MEMBER;

    override fun getAuthority(): String {
        return name
    }
}