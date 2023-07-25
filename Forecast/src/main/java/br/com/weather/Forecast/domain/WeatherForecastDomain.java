package br.com.weather.Forecast.domain;

import br.com.weather.Forecast.dto.WeatherForecastDto;
import br.com.weather.Forecast.model.Weather;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public interface WeatherForecastDomain {

	WeatherForecastDto get( String city, LocalDate datePrevision);

	Weather getByCity( String city);

	Weather cadastrarDados( Weather weatherEntity);

	 List<Weather> getAll( );

	Optional< Weather > deleteById( Long id );

	Weather update( Long id, Weather weather );
}
