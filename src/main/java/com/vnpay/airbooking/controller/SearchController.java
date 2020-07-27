package com.vnpay.airbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnpay.airbooking.entity.Airport;
import com.vnpay.airbooking.entity.SearchForm;
import com.vnpay.airbooking.entity.SearchRequest;
import com.vnpay.airbooking.entity.SearchResponse;
import com.vnpay.airbooking.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private AirportService airportService;

    @GetMapping(value = "/")
    public String test_json(Model model){
        List<Airport> airports= airportService.getAllAirport();
        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("airports", airports);
        return "DatVe";
    }

    @PostMapping(value = "/search")
    public String search_flight(Model model, @ModelAttribute("searchForm") SearchForm searchForm){
        String url = "https://vnticket.vnpaytest.vn/vnticketapi/search_flight_qh?data={data}";
        RestTemplate restTemplate = new RestTemplate();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        if(searchForm.getType_fight().equals("1")){
            searchForm.setReturn_date("");
        } else {
            searchForm.setReturn_date(simpleDateFormat.format(new Date(searchForm.getReturn_date())));
        }
        searchForm.setOrig_date(simpleDateFormat.format(new Date(searchForm.getOrig_date())));



        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setForm(searchForm);


        ObjectMapper objectMapper = new ObjectMapper();
        String json_request ="";
        String result = "";
        try {
            json_request = objectMapper.writeValueAsString(searchRequest);

            result = restTemplate.getForObject(url, String.class, json_request);

            SearchResponse searchResponse = objectMapper.readValue(result, SearchResponse.class);

            model.addAttribute("text", searchResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return "call_api";
    }

    @GetMapping(value = "/DatVe-B2")
    public String show_result(){

        return "DatVe-B2";
    }



}
