package home.persistence.services;

import home.domain.dto.PlaceDto;
import home.domain.model.Place;
import home.persistence.repositories.PlaceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlacesServiceTest {
    @Mock
    private PlaceRepository placeRepository;
    @InjectMocks
    private PlacesService placesService;

    @Test
    public void testGetPlaces() {
        Place onePlace = new Place();
        onePlace.setName("one");
        Place twoPlace = new Place();
        twoPlace.setName("second");
        placeRepository.save(onePlace);
        placeRepository.save(twoPlace);
        List<PlaceDto> expectedPlaceDtosList = new ArrayList<>();
        expectedPlaceDtosList.add(PlaceDto.builder().id(1L).name("one").build());
        List<Place> placeList = new ArrayList<>();
        onePlace.setId(1L);
        placeList.add(onePlace);
        when(placeRepository.findPlaces(any())).thenReturn(placeList);
        Assert.assertEquals(placesService.getPlaces("one"), expectedPlaceDtosList);

    }
}
