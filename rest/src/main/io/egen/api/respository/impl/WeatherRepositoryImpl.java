package io.egen.api.respository.impl;

import io.egen.api.entitiy.Weather;
import io.egen.api.respository.WeatherRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Weather> findAll() {
        TypedQuery<Weather> query = em.createQuery("SELECT u FROM Weather u", Weather.class);
        return query.getResultList();
    }

    public Weather create(Weather weather) {
        em.persist(weather);
        return weather;
    }

    public List<String> findAllCities()  {
        TypedQuery<String> query = em.createNamedQuery("Weather.findAllCities", String.class);
        return query.getResultList();
    }

    public Weather findCityByName(String cityName) {
        TypedQuery<Weather> query = em.createNamedQuery("Weather.findCityByName", Weather.class);
        query.setParameter("pCity", cityName);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public String findCityByNameAndProperty(String cityName, String property) {
        String query = "SELECT w." + property + " FROM Weather w  where w.city = '" + cityName + "' order by w.timestamp desc";
        TypedQuery<String> typedQuery = em.createQuery(query, String.class);
        typedQuery.setMaxResults(1);
        return typedQuery.getSingleResult();
    }

}
