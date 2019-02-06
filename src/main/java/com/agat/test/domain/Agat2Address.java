package com.agat.test.domain;


import javax.persistence.*;

@Entity
@Table(name = "ADDRESS", schema = "AGATMIN" )

public class Agat2Address {

        @Id
        @Column(name = "ID")
        private Integer id;
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

        public Agat2Address () {
        }

        public Agat2Address (Integer id, Integer pid, Integer type_address_id, String locality,
                             String street, Integer house, Integer body,
                             Integer apartment, Agat2TypeAddress agat2TypeAddress) {
            this.id = id;
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
}
