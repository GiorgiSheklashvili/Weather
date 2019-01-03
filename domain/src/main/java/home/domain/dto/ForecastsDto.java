package home.domain.dto;

import home.domain.model.Forecast;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ForecastsDto {
    Long id;
    private List<ForecastDto> forecastDtos;
}
