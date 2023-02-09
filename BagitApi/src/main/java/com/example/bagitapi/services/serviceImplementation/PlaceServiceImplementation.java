package com.example.bagitapi.services.serviceImplementation;




import com.example.bagitapi.Exceptions.ResourceNotFoundException;
import com.example.bagitapi.repositories.PlacesRepository;
import com.example.bagitapi.services.interfaces.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bagitapi.entities.Place;

import java.util.HashSet;
import java.util.Set;


@Service
public class PlaceServiceImplementation implements PlaceService {

    private PlacesRepository placesRepository;

    @Autowired
    public PlaceServiceImplementation(PlacesRepository placesRepository) {

        this.placesRepository = placesRepository;

    }


    @Override
    public Set<Place> findAllPlaces() {

        Set<Place> places = new HashSet<>();

        placesRepository.findAll().forEach(place -> places.add(place));

        return places;

    }

    @Override
    public Place findPlaceByName(String name) {

        Place place = placesRepository.findByName(name);

        if (place == null) {
            throw new ResourceNotFoundException("place not found in database");
        }

        return place;

    }

    @Override
    public Boolean createPlace(Place place) {

        Place result = placesRepository.save(place);

        if (result == null) {
            return false;
        }

        return true;
    }

    public Boolean delete(String name) {

        Place place = placesRepository.findByName(name);

        if (place == null) {
            throw new ResourceNotFoundException("place not found in database");
        }

        placesRepository.delete(place);

        return true;

    }

}
