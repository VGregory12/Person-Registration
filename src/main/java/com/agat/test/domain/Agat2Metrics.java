package com.agat.test.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "METRICS", schema = "AGATMIN")

public class Agat2Metrics {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PID")
    private Integer pid;
    @Column(name = "TYPE_METRICS_ID")
    private Integer typeMetricsId;

    public Agat2Metrics (){

    }

    public Agat2Metrics (Integer pid, Integer typeMetricsId,   Agat2TypeMetrics agat2TypeMetrics){

        this.pid = pid;
        this.typeMetricsId = typeMetricsId;

    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "TYPE_METRICS_ID", nullable = false, insertable = false, updatable = false)
    private Agat2TypeMetrics agat2TypeMetrics;


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

    public Integer getTypeMetricsId() {
        return typeMetricsId;
    }

    public void setTypeMetricsId(Integer typeMetricsId) {
        this.typeMetricsId = typeMetricsId;
    }

    public Agat2TypeMetrics getAgat2TypeMetrics() {
        return agat2TypeMetrics;
    }

    public void setAgat2TypeMetrics(Agat2TypeMetrics agat2TypeMetrics) {
        this.agat2TypeMetrics = agat2TypeMetrics;
    }
}
