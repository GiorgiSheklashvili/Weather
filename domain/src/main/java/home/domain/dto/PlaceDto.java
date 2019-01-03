package home.domain.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class PlaceDto {
    Long id;
    private String phenomenon;
    private String tempmin;
    private String tempmax;
    private String name;
}
