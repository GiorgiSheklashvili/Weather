package home.domain.dto;

import home.domain.model.Day;
import home.domain.model.Night;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ForecastDto {
    Long id;
    private Night night;
    private Day day;
    private String date;
}
