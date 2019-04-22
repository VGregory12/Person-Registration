package com.agat.test.domain;

import javax.persistence.*;

@Entity
@Table(name = "HISTORY_ADDRESS", schema = "AGATMIN" )

public class Agat2HistoryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ID_KEY")
    private Integer id_key;
    @Column(name = "PID")
    private Integer pid;
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

    public Agat2HistoryAddress() {
    }

    public Agat2HistoryAddress(Integer id_key,
                               Integer pid,
                               Integer type_address_id,
                               String locality,
                               String street,
                               Integer house,
                               Integer body,
                               Integer apartment,
                               Agat2TypeAddress agat2TypeAddress) {
        this.id_key = id_key;
        this.pid = pid;
        this.type_address_id = type_address_id;
        this.apartment = apartment;
        this.locality = locality;
        this.street = street;
        this.house = house;
        this.body = body;
        this.apartment = apartment;
        this.agat2TypeAddress = agat2TypeAddress;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "TYPE_ADDRESS_ID", nullable = false, insertable = false, updatable = false)
    private Agat2TypeAddress agat2TypeAddress;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_key() {
        return id_key;
    }

    public void setId_key(Integer id_key) {
        this.id_key = id_key;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public Agat2TypeAddress getAgat2TypeAddress() {
        return agat2TypeAddress;
    }

    public void setAgat2TypeAddress(Agat2TypeAddress agat2TypeAddress) {
        this.agat2TypeAddress = agat2TypeAddress;
    }

}
