package br.com.weather.Forecast.domain;

import br.com.weather.Forecast.dto.WeatherForecastDto;
import br.com.weather.Forecast.exception.NotFoundException;
import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Component
public class WeatherForecastDomainImpl implements  WeatherForecastDomain {

	@Autowired
	WeatherForecastService weatherForecastService;


	@Override
	public WeatherForecastDto get( String city, LocalDate date ) {
		List< Weather > optionalWeather = weatherForecastService.findByCityAndDatePrevision( city, date );
		if ( !( optionalWeather == null ) ) {
			Weather weatherResult = ( Weather ) optionalWeather;
			WeatherForecastDto weatherForecastDto = new WeatherForecastDto();
			weatherForecastDto.setCity(weatherResult.getCity());
			weatherForecastDto.setDatePrevision(weatherResult.getDate());
			weatherForecastDto.setHumidity(weatherResult.getHumidity());
			weatherForecastDto.setTemperature(weatherResult.getTemperature());
			weatherForecastDto.setMoreInformation(weatherResult.getMoreInformation());
			return weatherForecastDto;
		} else {

			 throw new NotFoundException( "Não encontrado banco dados");
		}

	}

	public List<Weather> getAll( ){
		return weatherForecastService.getAllWeatherForecast();
	}

	@Override
	public Weather cadastrarDados( Weather weatherEntity ) {
		return null;
	}
}
