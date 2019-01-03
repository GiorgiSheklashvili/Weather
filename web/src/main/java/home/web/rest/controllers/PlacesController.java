package home.web.rest.controllers;


import home.domain.dto.PlaceDto;
import home.persistence.services.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlacesController {
    @Autowired
    PlacesService placesService;

    @GetMapping
    public ResponseEntity<List<PlaceDto>> getAllPurchaseOrders(@RequestParam(name = "name") String name) {
        List<PlaceDto> places = placesService.getPlaces(name);
        if (places.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(placesService.getPlaces(name), HttpStatus.OK);
    }
}
