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

    public Agat2History () {}

    public Agat2History (Integer pid, String surname) {
        this.pid = pid;
        this.surname = surname;
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
}
