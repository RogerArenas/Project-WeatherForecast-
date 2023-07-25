package br.com.weather.Forecast.service;

import br.com.weather.Forecast.exception.NotFoundException;
import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.repository.WeatherRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	public void deleteById( Long id ) {
	 repository.deleteById( id );
	}

	public Optional< Weather > findById( Long id ) {
		return repository.findById( id );
	}



	public Weather update(Long id, Weather updatedWeather) {
		// Buscar o registro no banco de dados
		Optional<Weather> optional = findById(id);
		if (optional.isPresent()) {
			// Se o registro existir, obter o objeto Weather atual do banco de dados
			Weather existingWeather = optional.get();

			// Atualizar os atributos do objeto Weather existente com os valores do objeto atualizado
			existingWeather.setCity(updatedWeather.getCity());
			existingWeather.setDatePrevision(updatedWeather.getDatePrevision());
			existingWeather.setTemperature(updatedWeather.getTemperature());
			existingWeather.setHumidity(updatedWeather.getHumidity());
			existingWeather.setMoreInformation(updatedWeather.getMoreInformation());

			// Salvar as alterações no repositório
			return repository.save(existingWeather);
		} else {
			// Se o registro não for encontrado, retornar null ou lançar uma exceção, conforme sua preferência
			throw new NotFoundException( "Registro não encontrado no banco dados" ); // ou lançar uma exceção, como EntityNotFoundException
		}
	}



}