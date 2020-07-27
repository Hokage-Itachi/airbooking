package com.vnpay.airbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class SearchForm {
    private String orig_place;
    private String des_place;
    private String type_fight;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate orig_date;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate des_date;
    private String orig_date;
    private String return_date;
    private int adult_number = 1;
    private int child_number;
    private int baby_number;

    public String getType_fight() {
        return type_fight;
    }

    public void setType_fight(String type_fight) {
        this.type_fight = type_fight;
    }

    public String getOrig_place() {
        return orig_place;
    }

    public void setOrig_place(String orig_place) {
        this.orig_place = orig_place;
    }

    public String getDes_place() {
        return des_place;
    }

    public void setDes_place(String des_place) {
        this.des_place = des_place;
    }

    //
//    public LocalDate getOrig_date() {
//        return orig_date;
//    }
//
//    public void setOrig_date(LocalDate orig_date) {
//        this.orig_date = orig_date;
//    }
//
//    public LocalDate getDes_date() {
//        return des_date;
//    }

//    public void setDes_date(LocalDate des_date) {
//        this.des_date = des_date;
//    }


    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getOrig_date() {
        return orig_date;
    }



    public void setOrig_date(String orig_date) {
        this.orig_date = orig_date;
    }

    public int getAdult_number() {
        return adult_number;
    }

    public void setAdult_number(int adult_number) {
        this.adult_number = adult_number;
    }

    public int getChild_number() {
        return child_number;
    }

    public void setChild_number(int child_number) {
        this.child_number = child_number;
    }

    public int getBaby_number() {
        return baby_number;
    }

    public void setBaby_number(int baby_number) {
        this.baby_number = baby_number;
    }

    @Override
    public String toString() {
        return "BookingForm{" +
                "orig_place='" + orig_place + '\'' +
                ", des_place='" + des_place + '\'' +
                ", orig_date='" + orig_date + '\'' +
                ", des_date='" + return_date + '\'' +
                ", adult_number=" + adult_number +
                ", child_number=" + child_number +
                ", baby_number=" + baby_number +
                '}';
    }
}
