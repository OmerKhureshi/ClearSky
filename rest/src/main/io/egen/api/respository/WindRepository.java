package io.egen.api.respository;

import io.egen.api.entitiy.Wind;

import java.util.List;

public interface WindRepository {

    public List<Wind> findAll();


    public Wind create(Wind wind);

}
