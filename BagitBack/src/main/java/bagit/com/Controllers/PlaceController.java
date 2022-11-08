package bagit.com.Controllers;

import bagit.com.entities.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import bagit.com.services.interfaces.PlaceService;

import java.util.Set;

@Controller
@RequestMapping("/places/v1")
public class PlaceController {

    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping("/findAll")
    public ResponseEntity<Set<Place>> findAllPlaces () {

        return new ResponseEntity<>(placeService.findAllPlaces(), HttpStatus.OK);
    }

    @RequestMapping("/find/{name}")
    public ResponseEntity<Place> findPlaceByName(@PathVariable String name) {

        return new ResponseEntity<>(placeService.findPlaceByName(name), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Boolean> createNewPlace(@RequestBody Place place) {

        Boolean result = (placeService.createPlace(place) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/update/name/{name}")
    public ResponseEntity<Boolean> updatePlaceName(@PathVariable String name, @RequestBody String newName) {

        Boolean result = (placeService.updatePlaceName(name, newName) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);

    }

    @PutMapping("/update/name/description/{name}")
    public ResponseEntity<Boolean> updatePlaceDescription(@PathVariable String name, @RequestBody String description) {

        Boolean result = (placeService.updatePlaceDescription(name, description) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/update/name/video/{name}")
    public ResponseEntity<Boolean> updatePlaceVideo(@PathVariable String name, @RequestBody String video) {

        Boolean result = (placeService.updatePlaceVideo(name, video) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/update/name/navigator/{name}")
    public ResponseEntity<Boolean> updatePlaceNavigator(@PathVariable String name, @RequestBody String navigator) {

        Boolean result = (placeService.updatePlaceNavigator(name, navigator) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Boolean> deletePlace(@PathVariable String name) {

        Boolean result = (placeService.delete(name) == true) ? true : false;

        return new ResponseEntity<>(result,(result == true) ? HttpStatus.OK : HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/comment/{name}")
    public ResponseEntity<Boolean> postComment(@PathVariable String name, @RequestBody String comment) {

        Boolean result = placeService.addComment(name, comment);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }



}
