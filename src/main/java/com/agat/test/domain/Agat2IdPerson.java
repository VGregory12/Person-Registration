package com.agat.test.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "ID_PERSON", schema = "AGATMIN")

public class Agat2IdPerson {
        @Id
//        @GeneratedValue(strategy= GenerationType.AUTO)
        @Column(name = "PID")
        private Integer pid;
        @Column(name = "ENTRY_DATE")
        private Date entry_date;
        @Column(name = "USER_ID")
        private Integer user_id;


    public Agat2IdPerson() {
    }

    public Agat2IdPerson (Date entry_date, Integer user_id
//            , Agat2Address agat2Address
    ) {
        this.entry_date = entry_date;
        this.user_id = user_id;
//        this.agat2Address = agat2Address;
    }

    public Agat2IdPerson ( Integer pid, Date entry_date, Integer user_id
//            , Agat2Address agat2Address
    ) {
        this.pid = pid;
        this.entry_date = entry_date;
        this.user_id = user_id;
//        this.agat2Address = agat2Address;
    }

    public Agat2IdPerson (Date entry_date, Integer user_id
                          ,Agat2Person agat2Person,
                          Set<Agat2Document> agat2Document,
                          Set<Agat2Address> agat2Address


    ) {
        this.entry_date = entry_date;
        this.user_id = user_id;
//        this.agat2Person = agat2Person;
        this.agat2Document = agat2Document;
        this.agat2Address = agat2Address;

    }

    public Agat2IdPerson (Integer pid, Date entry_date, Integer user_id
            ,Agat2Person agat2Person,
                          Set<Agat2Document> agat2Document,
                          Set<Agat2Address> agat2Address

    ) {
        this.pid = pid;
        this.entry_date = entry_date;
        this.user_id = user_id;
//        this.agat2Person = agat2Person;
        this.agat2Document = agat2Document;
        this.agat2Address = agat2Address;

    }

    @OneToOne(mappedBy = "agat2IdPerson", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PID", nullable = false)
    private Agat2Person agat2Person;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PID", nullable = false, insertable = false, updatable = false)
    private Set<Agat2Document> agat2Document;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PID", nullable = false, insertable = false, updatable = false)
    private Set<Agat2Address> agat2Address;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PID", nullable = false, insertable = false, updatable = false)
    private Set<Agat2Metrics> agat2Metrics;






    public Integer getPid() {
                return pid;
        }


        public void setPid(Integer pid) {
                this.pid = pid;
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

        public Agat2Person getAgat2Person() {
            return agat2Person;
        }

        public void setAgat2Person(Agat2Person agat2Person) {
            this.agat2Person = agat2Person;
        }

    public Set<Agat2Document> getAgat2Document() {
        return agat2Document;
    }

    public void setAgat2Document(Set<Agat2Document> agat2Document) {
        this.agat2Document = agat2Document;
    }

    public Set<Agat2Address> getAgat2Address() {
        return agat2Address;
    }

    public void setAgat2Address(Set<Agat2Address> agat2Address) {
        this.agat2Address = agat2Address;
    }

    public Set<Agat2Metrics> getAgat2Metrics() {
        return agat2Metrics;
    }

    public void setAgat2Metrics(Set<Agat2Metrics> agat2Metrics) {
        this.agat2Metrics = agat2Metrics;
    }
}


