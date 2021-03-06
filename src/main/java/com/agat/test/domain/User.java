package com.agat.test.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


@Entity
@Table( name = "USR", schema = "AGATMIN")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator = "TED", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "TED", sequenceName = "AGATMIN.TED")
    private Integer id;
//    @Column(name = "USERNAME")
    private String username;
//    @Column(name = "PASSWORD")
    private String password;
//    @Column(name = "ACTIVE")
    private boolean active;
    private String name;
    private  String surname;
    private Date entry_date;
    private String email;
    private String activationCode;
    private Integer emailactive;
//


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
    private Set<Role>   roles;


    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Integer getEmailactive() {
        return emailactive;
    }

    public void setEmailactive(Integer emailactive) {
        this.emailactive = emailactive;
    }
}
