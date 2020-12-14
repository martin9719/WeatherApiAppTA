package com.tts.weather_app.repository;

import java.util.List;

import com.tts.weather_app.model.ZipCode;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCode, Long> {

    // public List<ZipCode> findAllByzipcodeInOrderByCreatedAtDesc();
    public List<ZipCode> findAllByOrderByCreatedAtDesc();

    public List<ZipCode> findAll();

}
