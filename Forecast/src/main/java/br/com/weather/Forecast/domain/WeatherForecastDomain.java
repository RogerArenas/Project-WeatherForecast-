package br.com.weather.Forecast.domain;

import br.com.weather.Forecast.dto.WeatherForecastDto;
import br.com.weather.Forecast.model.Weather;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface WeatherForecastDomain {

	WeatherForecastDto get( String city, LocalDate date);

	Weather cadastrarDados( Weather weatherEntity);

	 List<Weather> getAll( );
}
