package com.vnpay.airbooking.entity;

import javax.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "airport_code")
    private String airport_code;

    @Column(name = "airport_name")
    private String airport_name;

    public void setAirport_code(String airport_code) {
        this.airport_code = airport_code;
    }

    public String getAirport_code() {
        return airport_code;
    }

    public void setAirport_name(String airport_name) {
        this.airport_name = airport_name;
    }

    public String getAirport_name() {
        return airport_name;
    }

    @Override
    public String toString() {
        return  airport_name + "( " + airport_code +" )";
    }
}
