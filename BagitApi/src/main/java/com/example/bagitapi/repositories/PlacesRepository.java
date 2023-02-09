package com.example.bagitapi.repositories;

import com.example.bagitapi.entities.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlacesRepository extends CrudRepository<Place, Integer> {

    Place findByName(String name);

}
