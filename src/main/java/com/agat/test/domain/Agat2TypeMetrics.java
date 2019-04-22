package com.agat.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TYPE_METRICS", schema = "AGATMIN")

public class Agat2TypeMetrics {

    @Id
    @Column(name = "TYPE_METRICS_ID")
    private Integer typeMetricsId;
    @Column(name = "TYPE_METRICS")
    private String typeMetrics;



    public Agat2TypeMetrics() {
    }

    public Agat2TypeMetrics(Integer typeMetricsId, String typeMetrics){
        this.typeMetricsId = typeMetricsId;
      this.typeMetrics = typeMetrics;
    }

    public Integer getTypeMetricsId() {
        return typeMetricsId;
    }

    public void setTypeMetricsId(Integer typeMetricsId) {
        this.typeMetricsId = typeMetricsId;
    }

    public String getTypeMetrics() {
        return typeMetrics;
    }

    public void setTypeMetrics(String typeMetrics) {
        this.typeMetrics = typeMetrics;
    }


}
