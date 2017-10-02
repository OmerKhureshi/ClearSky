package io.egen.api.service.impl;

import io.egen.api.entitiy.Weather;
import io.egen.api.respository.WeatherRepository;
import io.egen.api.service.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    private WeatherRepository repository;

    public WeatherServiceImpl(WeatherRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Weather> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<String> findAllCities() {
        return repository.findAllCities();
    }

    @Transactional
    public Weather create(Weather weather) {
        return repository.create(weather);
    }

    @Override
    public String findCityByNameAndProperty(String cityName, String property) {
        return repository.findCityByNameAndProperty(cityName, property);
    }

    @Override
    public Weather findCityByName(String cityName) {
        return repository.findCityByName(cityName);
    }
}