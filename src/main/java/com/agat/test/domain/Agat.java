/*
package com.agat.test.domain;


import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

@Entity
@Table(name = "PERSON", schema = "AGATMIN")
public class Agat {

    @Id
    @Column(name = "PASSPORT_ID")
    private Integer PASSPORT_ID;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PHONE_NUMBER")
    private Integer phone;
    @Column(name = "WORK_ID")
    private Integer WORK_ID;

    public Agat() {
    }

    public Agat(Integer PASSPORT_ID, String name, String surname, Integer phone,
                Integer WORK_ID, Gender gender) {
        this.PASSPORT_ID = PASSPORT_ID;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.WORK_ID = WORK_ID;
        this.gender = gender;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENDER_ID", nullable = false)
    private Gender gender;

    public Integer getID() {
        return PASSPORT_ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getPhone() {
        return phone;
    }

    public Integer getWORK_ID() {
        return WORK_ID;
    }

    public void setID(Integer PASSPORT_ID) {
        this.PASSPORT_ID = PASSPORT_ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setWORK_ID(Integer WORK_ID) {
        this.WORK_ID = WORK_ID;
    }
}
*/
