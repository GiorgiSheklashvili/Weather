package home.persistence.repositories;

import home.domain.model.Forecast;
import home.domain.model.Forecasts;
import home.domain.model.Place;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {
    @Query("SELECT p FROM Place p where p.name=?1 and p.id>((SELECT MAX(p.id) from Place p)-12)")
    List<Place> findPlaces(String place);
}
