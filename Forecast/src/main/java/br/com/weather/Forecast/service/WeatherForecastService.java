package br.com.weather.Forecast.service;

import br.com.weather.Forecast.dto.WeatherForecastDto;
import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherForecastService {

	private final WeatherRepository repository;

	@Autowired
	public WeatherForecastService(WeatherRepository repository) {
		this.repository = repository;
	}

	public List<Weather> findByCityAndDatePrevision(String city, LocalDate datePrevision) {
		Optional<Weather> optionalWeather = repository.findByCityAndDate(city, datePrevision);
		if (optionalWeather.isPresent()) {
			Weather weatherResult = optionalWeather.get();
			WeatherForecastDto weatherForecastDto = new WeatherForecastDto();
			weatherForecastDto.setCity(weatherResult.getCity());
			weatherForecastDto.setDatePrevision(weatherResult.getDate());
			weatherForecastDto.setHumidity(weatherResult.getHumidity());
			weatherForecastDto.setTemperature(weatherResult.getTemperature());
			weatherForecastDto.setMoreInformation(weatherResult.getMoreInformation());
			return Collections.singletonList( weatherResult);
		} else {
			return Collections.emptyList();
		}
	}

	public List<Weather> cadastrarDados( List< Weather > weatherEntity) {

		List<Weather> weathers = new ArrayList<>(  );
		for(Weather weather: weatherEntity){
			Weather savedWeather =  repository.save(weather);
			weathers.add( savedWeather );
		}
		return weathers;
	}

	public List<Weather> getAllWeatherForecast(){
		return repository.findAll();
	}
}