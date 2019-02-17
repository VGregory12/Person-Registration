package com.agat.test.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "DOCUMENT", schema = "AGATMIN")

public class Agat2Document {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PID")
    private Integer pid;
    @Column(name = "DOC_NUMBER")
    private Integer doc_number;
    @Column (name = "DOC_TYPE")
    private String doc_type;
    @Column (name = "DATE_RECEIVING")
    private Date date_receiving;
    @Column (name = "ENTRY_DATE")
    private Date entry_date;
    @Column (name = "USER_ID")
    private Integer user_id;

    public Agat2Document (){

    }

    public Agat2Document (Integer pid, Integer doc_number, String doc_type, Date date_receiving,
                          Date entry_date, Integer user_id){

        this.pid = pid;
        this.doc_number = doc_number;
        this.doc_type = doc_type;
        this.date_receiving = date_receiving;
        this.entry_date = entry_date;
        this.user_id = user_id;
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

    public Integer getDoc_number() {
        return doc_number;
    }

    public void setDoc_number(Integer doc_number) {
        this.doc_number = doc_number;
    }

    public Date getDate_receiving() {
        return date_receiving;
    }

    public void setDate_receiving(Date date_receiving) {
        this.date_receiving = date_receiving;
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

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }
}