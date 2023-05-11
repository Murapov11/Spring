package com.example.producingwebservice;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import io.spring.guides.gs_producing_web_service.City;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CityRepository {
    private static final Map<String,City> cities = new HashMap<>();

    @PostConstruct
    public void initData() {
        City almaty = new City();
        almaty.setName("Almaty");
        almaty.setDescription("Sunny");
        almaty.setTemperature(38);

        cities.put(almaty.getName(), almaty);

        City taldyk  = new City();
        taldyk.setName("Taldykorgan");
        taldyk.setDescription("Cloudy");
        taldyk.setTemperature(30);
        cities.put(taldyk.getName(), taldyk);
    }

    public City findCity(String name) {
        Assert.notNull(name, "The city's name must not be null");
        return cities.get(name);
    }
}