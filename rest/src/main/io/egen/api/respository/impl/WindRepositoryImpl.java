package io.egen.api.respository.impl;

import io.egen.api.entitiy.Weather;
import io.egen.api.entitiy.Wind;
import io.egen.api.respository.WeatherRepository;
import io.egen.api.respository.WindRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class WindRepositoryImpl implements WindRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Wind> findAll() {
        TypedQuery<Wind> query = em.createQuery("SELECT u FROM Wind u", Wind.class);
        return query.getResultList();
    }

    public Wind create(Wind Wind) {
        em.persist(Wind);
        return Wind;
    }

}
