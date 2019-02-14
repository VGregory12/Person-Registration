//package com.agat.test.domain;
//
//
//import javax.persistence.*;
//import java.util.Date;
//
//
//@Entity(name = "Agat2IdPerson")
//@Table(name = "ID_PERSON", schema = "AGATMIN")
//
//public class Agat2IdPerson {
//        @Id
//        @Column(name = "PID")
//        private Integer pid;
//        @Column(name = "ENTRY_DATE")
//        private Date entry_date;
//        @Column(name = "USER_ID")
//        private Integer user_id;
//
//
//    public Agat2IdPerson() {
//    }
//
//    public Agat2IdPerson (Integer pid, Date entry_date, Integer user_id
////            , Agat2Address agat2Address
//    ) {
//        this.pid = pid;
//        this.entry_date = entry_date;
//        this.user_id = user_id;
////        this.agat2Address = agat2Address;
//    }
//
////    @OneToOne(cascade = CascadeType.ALL)
////    @PrimaryKeyJoinColumn
////    private Agat2Person agat2Person;
//
////    @OneToOne (fetch = FetchType.LAZY)
////    @JoinColumn(name = "PID", nullable = false)
////    private Agat2Address agat2Address;
//
//        public Integer getPid() {
//                return pid;
//        }
//
//
//        public void setPid(Integer pid) {
//                this.pid = pid;
//        }
//
//        public Date getEntry_date() {
//                return entry_date;
//        }
//
//        public void setEntry_date(Date entry_date) {
//                this.entry_date = entry_date;
//        }
//
//        public Integer getUser_id() {
//                return user_id;
//        }
//
//        public void setUser_id(Integer user_id) {
//                this.user_id = user_id;
//        }
//
//
//}
//
//
