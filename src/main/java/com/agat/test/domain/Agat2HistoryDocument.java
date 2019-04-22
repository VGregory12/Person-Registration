package com.agat.test.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "HISTORY_DOCUMENT", schema = "AGATMIN")

public class Agat2HistoryDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ID_KEY")
    private Integer id_key;
    @Column(name = "PID")
    private Integer pid;
    @Column(name = "DOC_NUMBER")
    private Integer doc_number;
    @Column(name = "DOC_TYPE")
    private String doc_type;
    @Column(name = "DATE_RECEIVING")
    private Date date_receiving;

    public Agat2HistoryDocument() {

    }

    public Agat2HistoryDocument(Integer id_key, Integer pid, Integer doc_number,
                                String doc_type, Date date_receiving) {
        this.id_key = id_key;
        this.pid = pid;
        this.doc_number = doc_number;
        this.doc_type = doc_type;
        this.date_receiving = date_receiving;
    }

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

}

