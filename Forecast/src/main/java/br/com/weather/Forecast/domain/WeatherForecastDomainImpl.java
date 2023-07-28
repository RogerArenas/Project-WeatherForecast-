package br.com.weather.Forecast.domain;

import br.com.weather.Forecast.dto.WeatherForecastDto;
import br.com.weather.Forecast.exception.NotFoundException;
import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class WeatherForecastDomainImpl implements  WeatherForecastDomain {

	@Autowired
	WeatherForecastService weatherForecastService;



	public WeatherForecastDto get( String city ) {
		Weather  optionalWeather = weatherForecastService.findByCity( city);
		if ( !( optionalWeather == null ) ) {
			Weather weatherResult = ( Weather ) optionalWeather;
			WeatherForecastDto weatherForecastDto = new WeatherForecastDto();
			weatherForecastDto.setCity(weatherResult.getCity());
			weatherForecastDto.setDatePrevision(weatherResult.getDatePrevision());
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
	public Optional< Weather > deleteById( Long id ) {

		Optional<Weather> optionalWeather = weatherForecastService.findById( id );
		if(optionalWeather.isPresent()){
			weatherForecastService.deleteById( id );
			return optionalWeather;
		}else {
			return Optional.empty();
		}
	}

	@Override
	public Weather update( Long id, Weather weather ) {
		return weatherForecastService.update( id, weather );
	}

	@Override
	public WeatherForecastDto get( String city, LocalDate datePrevision ) {
		return null;
	}

	@Override
	public Weather getByCity( String city ) {
		return  weatherForecastService.findByCity( city );
	}

	@Override
	public Weather cadastrarDados( Weather weatherEntity ) {
		return null;
	}


	public Optional< Weather> Update( Long id){
		return weatherForecastService.findById(id);
	}


}
