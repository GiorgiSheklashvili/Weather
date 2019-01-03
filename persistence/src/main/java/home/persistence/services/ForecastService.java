package home.persistence.services;

import home.domain.dto.ForecastDto;
import home.domain.model.Forecast;
import home.domain.model.Forecasts;
import home.domain.dto.ForecastsDto;
import home.persistence.repositories.ForecastsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@EnableScheduling
public class ForecastService {

    private static ForecastsRepository forecastsRepository;

    @Autowired
    public ForecastService(ForecastsRepository forecastsRepository) {
        this.forecastsRepository = forecastsRepository;
    }

    @Scheduled(fixedDelay = 1800000)
    public void scheduleFixedDelayTask() {
        Forecasts forecasts = parseForecastXml(getForecastXml());
        forecastsRepository.save(forecasts);
    }

    private static String getForecastXml() {
        final String url = "http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return res.getBody();
    }

    private static Forecasts parseForecastXml(String forecastXml) {
        Forecasts forecasts = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Forecasts.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader;
            String xmlString = forecastXml.substring(forecastXml.indexOf('\n') + 1);
            reader = new StringReader(xmlString);
            forecasts = (Forecasts) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forecasts;
    }

    public ForecastsDto getLatestForecast() {
        List<Forecasts> forecasts = forecastsRepository.findLatestForecasts();
        List<ForecastDto> forecastDtos = new ArrayList<>();
        for(Forecast forecast: forecasts.get(0).getForecast()){
            forecastDtos.add(ForecastDto.builder().id(forecast.getId())
                    .date(forecast.date)
                    .day(forecast.getDay())
                    .night(forecast.getNight()).build());
        }
        return ForecastsDto.builder().id(forecasts.get(0).getId()).forecastDtos(forecastDtos).build();
    }
}
