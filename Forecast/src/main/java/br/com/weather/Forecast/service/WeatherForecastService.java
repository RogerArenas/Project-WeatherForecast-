package br.com.weather.Forecast.service;

import br.com.weather.Forecast.exception.NotFoundException;
import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.repository.WeatherRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class WeatherForecastService {

	@Autowired
	WeatherRepository repository;


	@Autowired
	public WeatherForecastService(WeatherRepository repository) {
		this.repository = repository;
	}

	public Weather findByCity( String weather){
		Optional<Weather> optional = repository.findByCity( weather );
		if(optional.isPresent()) {
			Weather weatherResultado = optional.get();
			weatherResultado.setCity( weatherResultado.getCity( ) );
			return  weatherResultado;
		}
		else{
			 log.info("City not found");
		     throw new NotFoundException( "City not found in database" );
		}

	}
   /*
	public List<Weather> findByCityAndDatePrevision(String city, LocalDate datePrevision) {
		Optional<Weather> optionalWeather = repository.findByCityAndDate(city, datePrevision);
		if (optionalWeather.isPresent()) {
			Weather weatherResult = optionalWeather.get();
			weatherResult.setCity(weatherResult.getCity());
			weatherResult.setDatePrevision(weatherResult.getDatePrevision());
			weatherResult.setHumidity(weatherResult.getHumidity());
			weatherResult.setTemperature(weatherResult.getTemperature());
			weatherResult.setMoreInformation(weatherResult.getMoreInformation());
			return Collections.singletonList( weatherResult);
		} else {
			throw new NotFoundException( "Sem registro encontrados no banco dados" );

		}
	}
  */
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
/*
	public Weather update( Long id, Weather weather ) {
		Assert.notNull( id,"Não foi possivel atualizar o registro" );

		//Buscar o registro no banco dados
		List< Weather > optional = findByCityAndDatePrevision(String city, LocalDate datePrevision);
		if(optional.isPresent()) {

		}

		return repository.save( weather,id );
	}

 */
}