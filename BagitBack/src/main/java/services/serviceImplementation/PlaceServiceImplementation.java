package services.serviceImplementation;


import Exceptions.ResourceNotFoundException;
import entities.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PlacesRepository;
import services.interfaces.PlaceService;
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

        if (placesRepository.findByName(name) == null) {
            throw new ResourceNotFoundException("place not found in database");
        }

        return placesRepository.findByName(name);

    }

    @Override
    public Boolean createPlace(Place place) {

        Place result = placesRepository.save(place);

        if (result == null) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean updatePlaceName(String name, String newName) {

        Place place = placesRepository.findByName(name);

        if (place == null) {
            throw new ResourceNotFoundException("place not found in database");
        }

        place.setName(newName);

        place = placesRepository.save(place);

        if (place == null || place.getName().equals(name)) {
            return false;
        }

        return true;

    }

    @Override
    public Boolean updatePlaceDescription(String name, String description) {

        Place place = placesRepository.findByName(name);

        if (place == null) {
            throw new ResourceNotFoundException("place not found in database");
        }

        place.setDescription(description);

        place = placesRepository.save(place);

        if (place == null || !place.getDescription().equals(description)) {
            return false;
        }

        return true;

    }

    @Override
    public Boolean updatePlaceVideo(String name, String video) {

        Place place = placesRepository.findByName(name);

        if (place == null) {
            throw new ResourceNotFoundException("place not found in database");
        }

        place.setVideo(video);

        place = placesRepository.save(place);

        if (place == null || !place.getVideo().equals(video)) {
            return false;
        }

        return true;

    }

    @Override
    public Boolean updatePlaceNavigator(String name, String navigator) {

        Place place = placesRepository.findByName(name);

        if (place == null) {
            throw new ResourceNotFoundException("place not found in database");
        }

        place.setName(navigator);

        place = placesRepository.save(place);

        if (place == null || !place.getNavigator().equals(navigator)) {
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
