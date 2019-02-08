package com.agat.test.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table( name = "USR")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @Column(name = "USERNAME")
    private String username;
//    @Column(name = "PASSWORD")
    private String password;
//    @Column(name = "ACTIVE")
    private boolean active;

//    public User (){
//    }
//
//    public User (String username, String password, Boolean active){
//        this.username = username;
//        this.password = password;
//        this.active = active;
//    }
//    формирование таблицы по хранению енама
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    данное поле будет храниться в отдельной таблицы для которой мы не описывали мэпинг
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name ="user_id"))
//    хотим хранить енам в строковой
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null; ////если роль только пользователя
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}