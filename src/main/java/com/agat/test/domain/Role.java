package com.agat.test.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
//        return null;
//        делаем name, будет являться строковым представлением значения USER
        return name();
    }
}
