package home.web.rest.controllers;

import home.domain.dto.ForecastsDto;
import home.persistence.services.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/forecast")
public class ForecastController {
    @Autowired
    ForecastService forecastService;

    @GetMapping("/latest")
    public ResponseEntity<ForecastsDto> getAllPurchaseOrders(){
        return new ResponseEntity<>(forecastService.getLatestForecast(), HttpStatus.OK);
    }
}
