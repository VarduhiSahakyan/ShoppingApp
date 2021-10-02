package com.example.shoppingapplication.model.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
// TODO
public enum Role {
    ADMIN {
        @Override
        public Set<SimpleGrantedAuthority> getAuthorities() {
            return super.getAuthorities();
        }
    },
    USER{
        @Override
        public Set<SimpleGrantedAuthority> getAuthorities() {
            return super.getAuthorities();
        }
    };

    public Set<SimpleGrantedAuthority> getAuthorities() {
    return USER.getAuthorities();
    }
}
