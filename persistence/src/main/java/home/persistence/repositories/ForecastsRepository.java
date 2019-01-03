package home.persistence.repositories;

import home.domain.model.Forecast;
import home.domain.model.Forecasts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ForecastsRepository extends CrudRepository<Forecasts, Long> {
    @Query("SELECT u FROM Forecasts u where u.id=(SELECT MAX(u.id) from Forecasts u)")
    List<Forecasts> findLatestForecasts();

}
