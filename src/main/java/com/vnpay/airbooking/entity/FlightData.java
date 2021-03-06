package com.vnpay.airbooking.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class FlightData {
//    private Flight[] departureFlights;
//    private Flight[] returnFlights;
//
//
//    @Override
//    public String toString() {
//        return "FlightData{" +
//                "departureFlights=" + Arrays.toString(departureFlights) +
//                ", returnFlights=" + Arrays.toString(returnFlights) +
//                '}';
//    }
    private List<Flight>  departureFlights;
    private List<Flight>  returnFlights;

    @Override
    public String toString() {
        return "FlightData{" +
                "departureFlights=" + departureFlights +
                ", returnFlights=" + returnFlights +
                '}';
    }
}
