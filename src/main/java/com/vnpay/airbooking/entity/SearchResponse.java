package com.vnpay.airbooking.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponse {
    private String code;
    private String message;
    private String app_id;
    private String request_id;
    private String query_id;
    private FlightData flightData;
    private String checksum;
    private String action;

    @Override
    public String toString() {
        return "SearchResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", query_id='" + query_id + '\'' +
                ", flightData=" + flightData +
                '}';
    }
}
