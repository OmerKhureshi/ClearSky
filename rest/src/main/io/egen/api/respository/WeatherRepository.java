package io.egen.api.respository;

import io.egen.api.entitiy.Weather;

import java.util.List;

public interface WeatherRepository {

    public List<Weather> findAll();

    public List<String> findAllCities();

    public Weather create(Weather weather);

    Weather findCityByName(String cityName);

    String findCityByNameAndProperty(String cityName, String property);

}
