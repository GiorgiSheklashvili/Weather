package home.persistence.services;

import home.domain.model.Forecast;
import home.domain.model.Forecasts;
import home.persistence.repositories.ForecastsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ForecastServiceTest {
    @Mock
    private ForecastsRepository forecastsRepository;
    @InjectMocks
    private ForecastService forecastService;

    @Test
    public void testScheduleFixedDelayTask(){
        forecastService.scheduleFixedDelayTask();
        verify(forecastsRepository).save(any());
    }

    @Test
    public void testGetLatestForecast(){
        List<Forecast> forecastList = new ArrayList<>();
        forecastList.add(new Forecast());
        Forecasts firstForecasts = new Forecasts();
        Forecasts secondForecasts = new Forecasts();
        firstForecasts.setForecast(forecastList);
        secondForecasts.setForecast(forecastList);
        forecastsRepository.save(firstForecasts);
        forecastsRepository.save(secondForecasts);
        firstForecasts.setId(1L);
        secondForecasts.setId(2L);
        List<Forecasts> ExpectedForecastsList=new ArrayList<>();
        ExpectedForecastsList.add(secondForecasts);
        when(forecastsRepository.findLatestForecasts()).thenReturn(ExpectedForecastsList);
        Assert.assertEquals(forecastService.getLatestForecast().getId(),new Long(2));
    }
}
