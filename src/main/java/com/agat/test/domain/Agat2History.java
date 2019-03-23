package com.agat.test.domain;


import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name="HISTORY", schema = "AGATMIN")

public class Agat2History {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PID")
    private Integer pid;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PATRONYMIC")
    private String patronymic;
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Column(name = "IDENTIFIER")
    private String identifier;
    @Column(name = "DOC_NUMBER")
    private Integer doc_number;
    @Column (name = "DOC_TYPE")
    private String doc_type;
    @Column (name = "DATE_RECEIVING")
    private Date date_receiving;
    @Column(name = "TYPE_ADDRESS_ID")
    private Integer type_address_id;
    @Column(name = "LOCALITY")
    private String locality;
    @Column(name = "STREET")
    private String street;
    @Column(name = "HOUSE")
    private Integer house;
    @Column(name = "BODY")
    private Integer body;
    @Column(name = "APARTMENT")
    private Integer apartment;
    @Column (name = "ENTRY_DATE")
    private Date entry_date;
    @Column (name = "USER_ID")
    private Integer user_id;
    @Column (name = "ACTIVE")
    private Boolean active;

    public Agat2History () {}

    public Agat2History (Integer pid, String surname,  String name, String patronymic,
                         Date birthday, String identifier, Integer doc_number,
                         String doc_type, Date date_receiving, Integer type_address_id, String locality,
                         String street, Integer house, Integer body,
                         Integer apartment, Date entry_date, Integer user_id, Boolean active) {
        this.pid = pid;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.identifier = identifier;
        this.doc_number = doc_number;
        this.doc_type = doc_type;
        this.date_receiving = date_receiving;
        this.type_address_id = type_address_id;
        this.locality = locality;
        this.street = street;
        this.house = house;
        this.body = body;
        this.apartment = apartment;
        this.entry_date = entry_date;
        this.user_id = user_id;
        this.active = active;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getDoc_number() {
        return doc_number;
    }

    public void setDoc_number(Integer doc_number) {
        this.doc_number = doc_number;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public Date getDate_receiving() {
        return date_receiving;
    }

    public void setDate_receiving(Date date_receiving) {
        this.date_receiving = date_receiving;
    }

    public Integer getType_address_id() {
        return type_address_id;
    }

    public void setType_address_id(Integer type_address_id) {
        this.type_address_id = type_address_id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
