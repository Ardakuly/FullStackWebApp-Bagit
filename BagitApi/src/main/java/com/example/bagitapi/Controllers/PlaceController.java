package com.example.bagitapi.Controllers;

import com.example.bagitapi.entities.Image;
import com.example.bagitapi.entities.Place;
import com.example.bagitapi.services.interfaces.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bagit/v1")
public class PlaceController {

    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/places")
    public ResponseEntity<Boolean> createNewPlace(@RequestParam("name") String name,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("videoLink") String videoLink,
                                                  @RequestParam("navigatorLink") String navigatorLink,
                                                  @RequestParam("image") MultipartFile image) throws IOException {

        System.out.println("SENT");

        Place place = new Place();

        place.setName(name);
        place.setDescription(description);

        String fileName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();
        File file = new File("/Users/ardakuly/Desktop/projects/projectsCV/Bagit/BagitApi/src/main/resources/static/" + fileName);
        image.transferTo(file);

        Image data = new Image();
        data.setRealImage(fileName);

        place.setImage(data);
        place.setVideo(videoLink);
        place.setNavigator(navigatorLink);

        Boolean result = (placeService.createPlace(place) == true) ? true : false;

        if (result == true) {
            return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*").body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).header("Access-Control-Allow-Origin", "*").body(false);
        }

    }

    @RequestMapping("/places")
    public ResponseEntity<Set<Place>> findAllPlaces () {

        return new ResponseEntity<>(placeService.findAllPlaces(), HttpStatus.OK);
    }

    @RequestMapping("/places/{name}")
    public ResponseEntity<Place> findPlaceByName(@PathVariable String name) {

        return new ResponseEntity<>(placeService.findPlaceByName(name), HttpStatus.OK);
    }

    @RequestMapping("/images/{fileName}")
    public ResponseEntity<byte[]> findImage(@PathVariable String fileName) throws IOException {

        System.out.println(fileName);

        File file = new File("/Users/ardakuly/Desktop/projects/projectsCV/Bagit/BagitApi/src/main/resources/static/" + fileName);
        BufferedImage bImage = ImageIO.read(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);

    }



}

