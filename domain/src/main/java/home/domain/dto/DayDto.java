package home.domain.dto;

import home.domain.model.Place;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class DayDto {
    Long id;
    private String phenomenon;
    private String tempmax;
    private String tempmin;
    private String text;
    private List<PlaceDto> places;
}
