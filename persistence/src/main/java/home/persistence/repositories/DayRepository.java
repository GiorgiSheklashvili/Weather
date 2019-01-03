package home.persistence.repositories;

import home.domain.model.Day;
import home.domain.model.Forecast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {
}
