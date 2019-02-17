package com.agat.test.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "TYPE_ADDRESS", schema = "AGATMIN")

public class Agat2TypeAddress {

    @Id
    @Column (name = "TYPE_ADDRESS_ID")
    private Integer typeAddressId;
    @Column (name = "TYPE_ADDRESS")
    private String typeAddress;


    public Agat2TypeAddress () {
    }

    public Agat2TypeAddress (Integer typeAddressId, String typeaddress){
        this.typeAddressId = typeAddressId;
        this.typeAddress = typeAddress;
    }



    public Integer getTypeAddressId() {
        return typeAddressId;
    }

    public void setTypeAddressId(Integer typeAddressId) {
        this.typeAddressId = typeAddressId;
    }

    public String getTypeAddress() {
        return typeAddress;
    }

    public void setTypeAddress(String typeAddress) {
        this.typeAddress = typeAddress;
    }
}
