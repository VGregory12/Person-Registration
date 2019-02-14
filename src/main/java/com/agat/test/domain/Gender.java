//package com.agat.test.domain;
//
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "GENDER", schema = "AGATMIN")
//public class Gender {
//    @Id
//    @Column(name = "GENDER_ID")
//    private Integer GENDER_ID;
//    @Column(name = "NAME")
//    private String NAME;
//
//    public Gender() {
//    }
//
//    public Gender(Integer GENDER_ID, String NAME) {
//        this.GENDER_ID = GENDER_ID;
//        this.NAME = NAME;
//    }
//
////    @OneToMany(cascade = CascadeType.ALL,
////            fetch = FetchType.EAGER,
////            mappedBy = "gender")
////    private List<Agat> Persons;
//
//    public Integer getGENDER_ID() {
//        return GENDER_ID;
//    }
//
//    public String getNAME() {
//        return NAME;
//    }
//
//    public void setGENDER_ID(Integer GENDER_ID) {
//        this.GENDER_ID = GENDER_ID;
//    }
//
//    public void setNAME(String NAME) {
//        this.NAME = NAME;
//    }
//}