package com.pamihnenkov.supplier.security;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Enumerated;


public enum ApplicationGrantedAuthority implements GrantedAuthority {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    public final String name;

    ApplicationGrantedAuthority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
