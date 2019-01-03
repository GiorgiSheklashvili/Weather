package home.persistence.services;

import home.domain.model.Place;
import home.persistence.repositories.PlaceRepository;
import home.domain.dto.PlaceDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlacesService {

    PlaceRepository placeRepository;

    public PlacesService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<PlaceDto> getPlaces(String name){
        List<Place> places = placeRepository.findPlaces(name);
        List<PlaceDto> placeDtos = new ArrayList<>();
        for(Place place:places){
            placeDtos.add(PlaceDto.builder().id(place.getId())
                    .name(place.getName())
                    .phenomenon(place.getPhenomenon())
                    .tempmin(place.getTempmin())
                    .tempmax(place.getTempmax()).build());
        }
        return placeDtos;
    }
}
