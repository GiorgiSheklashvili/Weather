package home.persistence.repositories;

import home.domain.model.Forecast;
import home.domain.model.Night;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NightRepository extends CrudRepository<Night, Long> {
}
