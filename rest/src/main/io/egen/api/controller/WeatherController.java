package io.egen.api.controller;

import io.egen.api.constants.URI;
import io.egen.api.entitiy.Weather;
import io.egen.api.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = URI.WEATHER)
public class WeatherController {

    // api/weather/city
    // api/weather/city/:cityName
    // api/weather/city/:cityName?property=:propName

    private WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find All Weather entries", notes = "Returns a list of weather information stored in the DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public List<Weather> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Create Weather entries", notes = "Create a weather entry in the app")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public Weather create(@RequestBody Weather weather) {
        return service.create(weather);
    }

    @RequestMapping(value = URI.CITY, method = RequestMethod.GET)
    @ApiOperation(value = "Find the list of cities", notes = "Returns a list of all cities that have ever reported their weather in the past.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public List<String> findAllCities() {
        return service.findAllCities();
    }

    @RequestMapping(value = URI.CITY + "/{cityName}", method = RequestMethod.GET)
    @ApiOperation(value = "Find the latest weather for a given city", notes = "Returns the most recent weather information for the given city.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public Weather findCityByName(@PathVariable String cityName) {
        return service.findCityByName(cityName);
    }

    @RequestMapping(value = URI.CITY + "/{cityName}", params = URI.PROP, method = RequestMethod.GET)
    @ApiOperation(value = "Find a specific property of weather for a given city", notes = "Returns the latest weather property for a given city, e.g get the latest temperature for Chicago, get the latest humidity for Miami.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public String findCityByNameAndProperty(@PathVariable String cityName, @RequestParam(value = URI.PROP) String property) {
        return service.findCityByNameAndProperty(cityName, property);
    }
}