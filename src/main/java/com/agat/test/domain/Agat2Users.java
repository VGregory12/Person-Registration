package com.agat.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "USERS", schema = "AGATMIN")

public class Agat2Users {
    @Id
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "USER_ID")
    private Integer user_id;
    @Column(name = "ENTRY_DATE")
    private Date entry_date;

    public Agat2Users (){
    }

    public Agat2Users (String username,Integer user_id, Date entry_date){
        this.username = username;
        this.user_id = user_id;
        this.entry_date = entry_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }
}
