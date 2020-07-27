package com.vnpay.airbooking.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
public class Flight {

    private long flight_fare_id;
    private String airlineCode;
    private String depart_date;
    private String arrival_date;
    private String original;
    private String destination;
    private int stops;
    private String duration;
    private String currency;
    private int adult;
    private int child;
    private int infant;
    private long priceAdult;
    private long priceChild;
    private long priceInfant;
    private long feeAdult;
    private long feeChild;
    private long feeInfant;
    private long taxAdult;
    private long taxChild;
    private long taxInfant;
    private long feeVnpay;
    private long totalPrice;
    private FlightSegment[] FlightSegments;

    @Override
    public String toString() {
        return "Flight{" +
                "flight_fare_id=" + flight_fare_id +
                ", airlineCode='" + airlineCode + '\'' +
                ", depart_date='" + depart_date + '\'' +
                ", arrival_date='" + arrival_date + '\'' +
                ", original='" + original + '\'' +
                ", destination='" + destination + '\'' +
                ", stops=" + stops +
                ", duration='" + duration + '\'' +
                ", currency='" + currency + '\'' +
                ", adult=" + adult +
                ", child=" + child +
                ", infant=" + infant +
                ", priceAdult=" + priceAdult +
                ", priceChild=" + priceChild +
                ", priceInfant=" + priceInfant +
                ", feeAdult=" + feeAdult +
                ", feeChild=" + feeChild +
                ", feeInfant=" + feeInfant +
                ", taxAdult=" + taxAdult +
                ", taxChild=" + taxChild +
                ", taxInfant=" + taxInfant +
                ", feeVnpay=" + feeVnpay +
                ", totalPrice=" + totalPrice +
                ", FlightSegments=" + Arrays.toString(FlightSegments) +
                '}';
    }
}
