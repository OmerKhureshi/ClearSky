package io.egen.api.service;

import io.egen.api.entitiy.Weather;

import java.util.List;

public interface WeatherService {

    List<Weather> findAll();

    List<String> findAllCities();

    Weather create(Weather weather);

    Weather findCityByName(String cityName);

    String findCityByNameAndProperty(String cityName, String property);
}
