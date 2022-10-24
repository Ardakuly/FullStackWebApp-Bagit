package Controllers;

import entities.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.interfaces.PlaceService;

import java.util.Set;

@Controller
@RequestMapping("/places/v1")
public class PlaceController {

    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping("/places")
    public ResponseEntity<Set<Place>> findAllPlaces () {

        return new ResponseEntity<>(placeService.findAllPlaces(), HttpStatus.OK);
    }

    @RequestMapping("/places/{name}")
    public ResponseEntity<Place> findPlaceByName(@PathVariable String name) {

        return new ResponseEntity<>(placeService.findPlaceByName(name), HttpStatus.OK);
    }

    @PostMapping("/places/new")
    public ResponseEntity<Boolean> createNewPlace(@RequestBody Place place) {

        Boolean result = (placeService.createPlace(place) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/places/update/name/{name}")
    public ResponseEntity<Boolean> updatePlaceName(@PathVariable String name, @RequestBody String newName) {

        Boolean result = (placeService.updatePlaceName(name, newName) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);

    }

    @PutMapping("/places/update/name/{name}")
    public ResponseEntity<Boolean> updatePlaceDescription(@PathVariable String name, @RequestBody String description) {

        Boolean result = (placeService.updatePlaceDescription(name, description) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/places/update/name/{name}")
    public ResponseEntity<Boolean> updatePlaceVideo(@PathVariable String name, @RequestBody String video) {

        Boolean result = (placeService.updatePlaceVideo(name, video) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/places/update/name/{name}")
    public ResponseEntity<Boolean> updatePlaceNavigator(@PathVariable String name, @RequestBody String navigator) {

        Boolean result = (placeService.updatePlaceNavigator(name, navigator) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/places/{name}")
    public ResponseEntity<Boolean> deletePlace(@PathVariable String name) {

        Boolean result = (placeService.delete(name) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }










}
