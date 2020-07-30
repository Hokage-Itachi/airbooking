package com.vnpay.airbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnpay.airbooking.entity.*;
import com.vnpay.airbooking.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SearchController {

    @Autowired
    private AirportService airportService;

    ;

    public String toJSONString(SearchForm searchForm){
        String inputPattern = "MM/dd/yyyy";
        String outputPattern = "yyyy-MM-dd";

        if(searchForm.getType_fight().equals("1")){
            searchForm.setReturn_date("");
        } else {
            searchForm.setReturn_date(dateToString(stringToDate(searchForm.getReturn_date(), inputPattern),outputPattern));
        }
        searchForm.setOrig_date(dateToString(stringToDate(searchForm.getOrig_date(), inputPattern),outputPattern));


        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setForm(searchForm);
        ObjectMapper objectMapper = new ObjectMapper();
        String json_request ="";
        try {
            json_request = objectMapper.writeValueAsString(searchRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json_request;
    }

    public SearchResponse toObject(String json){
        SearchResponse searchResponse = new Gson().fromJson(json, SearchResponse.class);

        return searchResponse;

    }

    public Date stringToDate(String string, String pattern){
        try {
            return new SimpleDateFormat((pattern)).parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String dateToString(Date date, String pattern){
        return new SimpleDateFormat(pattern).format(date);
    }

    public void formatFlightDate(List<Flight> flights){
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy/HH:mm/ss";
        for (int i = 0; i < flights.size(); i++) {
            flights.get(i).setDepart_date(dateToString(stringToDate(flights.get(i).getDepart_date(), inputPattern), outputPattern));
            flights.get(i).setArrival_date(dateToString(stringToDate(flights.get(i).getArrival_date(),inputPattern),outputPattern));
        }
    }

    public Flight getFlight(Long id, List<Flight> flights){
        for (int i = 0; i < flights.size(); i++) {
            if(flights.get(i).getFlight_fare_id() == id){
                return flights.get(i);
            }
        }
        return null;
    }

    @GetMapping(value = "/")
    public String test_json(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Airport> airports= airportService.getAllAirport();
        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("airports", airports);
        return "DatVe";
    }

    @PostMapping(value = "/search")
    public String search_flight(Model model, @ModelAttribute("searchForm") SearchForm searchForm, HttpSession session){

        String url = "https://vnticket.vnpaytest.vn/vnticketapi/search_flight_qh?data={data}";
        RestTemplate restTemplate = new RestTemplate();
        // convert search data to json
        String data =  toJSONString(searchForm);

        String result = restTemplate.getForObject(url, String.class, data);

        SearchResponse searchResponse = toObject(result);
        formatFlightDate(searchResponse.getFlightData().getDepartureFlights());
        formatFlightDate(searchResponse.getFlightData().getReturnFlights());
        session.setAttribute("flightData",searchResponse.getFlightData());
        //model.addAttribute("text", searchResponse.getFlightData().getDepartureFlights()[0].toString());

        model.addAttribute("flightData", searchResponse.getFlightData());

        model.addAttribute("orig_place", airportService.findByCode(searchResponse.getFlightData().getDepartureFlights().get(0).getOriginal()).getAirport_name());
        model.addAttribute("des_place", airportService.findByCode(searchResponse.getFlightData().getDepartureFlights().get(0).getDestination()).getAirport_name());

        return "DatVe-B2";
    }

    @PostMapping(value = "/DatVe-B3")
    public String show_result(@RequestParam(name = "flight_fare_id") Long flight_fare_id, Model model, HttpSession session){

        FlightData flightData = (FlightData) session.getAttribute("flightData");
        Flight flightDetail = getFlight(flight_fare_id, flightData.getDepartureFlights());
        //flightDetail.setDepart_date(dateToString(stringToDate(flightDetail.getDepart_date(), inputPattern), outputPattern));
        model.addAttribute("flightDetails", flightDetail);
        model.addAttribute("orig_place", airportService.findByCode(flightDetail.getOriginal()));
        model.addAttribute("des_place", airportService.findByCode(flightDetail.getDestination()));





        return "DatVe-B3";
    }

    @GetMapping(value = "/test")
    public String testForm(){
        return "DatVe-B3";
    }
}