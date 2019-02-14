//package com.agat.test.domain;
//
//import javax.persistence.*;
//import java.util.Date;
//
//
//@Entity
//@Table(name = "PERSON", schema = "AGATMIN")
//
//public class Agat2Person {
//
//    @Id
//    @Column(name = "ID")
//    private Integer id;
//    @Column(name = "PID")
//    private Integer pid;
//    @Column(name = "SURNAME")
//    private String surname;
//    @Column(name = "NAME")
//    private String name;
//    @Column(name = "PATRONYMIC")
//    private String patronymic;
//    @Column(name = "BIRTHDAY")
//    private Date birthday;
//    @Column(name = "CAUSE_DEATH")
//    private String cause_death;
//    @Column(name = "DATE_DEATH")
//    private Date date_death;
//    @Column(name = "DATE_ENTER")
//    private Date date_enter;
//    @Column(name = "USER_ID")
//    private Integer user_id;
//    @Column(name = "IDENTIFIER")
//    private Integer identifier;
//
//    public Agat2Person() {
//    }
//
//    public Agat2Person(Integer id, Integer pid, String surname, String name, String patronymic,
//                       Date birthday, String cause_death, Date date_death,
//                       Date date_enter, Integer user_id, Integer identifier) {
//        this.id = id;
//        this.pid = pid;
//        this.name = name;
//        this.surname = surname;
//        this.patronymic = patronymic;
//        this.birthday = birthday;
//        this.cause_death = cause_death;
//        this.date_death = date_death;
//        this.date_enter = date_enter;
//        this.user_id = user_id;
//        this.identifier = identifier;
//    }
//
//
//
//    public Integer getId() {
//        return id;
//    }
//
//    public Integer getPid() {
//        return pid;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getPatronymic() {
//        return patronymic;
//    }
//
//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public String getCause_death() {
//        return cause_death;
//    }
//
//    public Date getDate_death() {
//        return date_death;
//    }
//
//    public Date getDate_enter() {
//        return date_enter;
//    }
//
//    public Integer getUser_id() {
//        return user_id;
//    }
//
//    public Integer getIdentifier() {
//        return identifier;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public void setPid(Integer pid) {
//        this.pid = pid;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPatronymic(String patronymic) {
//        this.patronymic = patronymic;
//    }
//
//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }
//
//    public void setCause_death(String cause_death) {
//        this.cause_death = cause_death;
//    }
//
//    public void setDate_death(Date date_death) {
//        this.date_death = date_death;
//    }
//
//    public void setDate_enter(Date date_enter) {
//        this.date_enter = date_enter;
//    }
//
//    public void setUser_id(Integer user_id) {
//        this.user_id = user_id;
//    }
//
//    public void setIdentifier(Integer identifier) {
//        this.identifier = identifier;
//    }
//}