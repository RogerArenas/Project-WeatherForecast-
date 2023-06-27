package br.com.weather.Forecast.controller;

import br.com.weather.Forecast.domain.WeatherForecastDomain;
import br.com.weather.Forecast.dto.WeatherForecastDto;
import br.com.weather.Forecast.dto.response.WeatherResponse;
import br.com.weather.Forecast.exception.NotFoundException;
import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.service.GetExternaApiWeather;
import br.com.weather.Forecast.service.WeatherForecastService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("api/v1")
@RestController
@Log4j2
public class WeatherForecastController {
    @Autowired
     GetExternaApiWeather getExternaApiWeather;
	@Autowired
	WeatherForecastService wfService;

	@Autowired
	WeatherForecastDomain weatherForecastDomain;

	@GetMapping("/{city}/{date}")
	public ResponseEntity< WeatherForecastDto > getWeatherForecast( @RequestHeader String city, @RequestHeader @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate datePrevision) {
		WeatherForecastDto dto = weatherForecastDomain.get( city, LocalDate.parse(String.valueOf( datePrevision ) ) );
		if (dto == null) {

			return ResponseEntity.notFound().build();
		} else {
			log.info( "Consulta dados banco : {}" + dto );
			return ResponseEntity.ok(dto);
		}
	}

	@GetMapping
	public ResponseEntity<List<Weather>> getWeatherForecastAll() {
		List< Weather > weather = weatherForecastDomain.getAll( );
		if ( weather.isEmpty( ) ) {
			log.info( "Sem dados cadastrados no banco" );
			return ResponseEntity.notFound( ).build( );
		}

		log.info( "Dados consultados do banco: {}", weather );
		return ResponseEntity.ok( weather );
	}

	@GetMapping("/{city}")
	public WeatherResponse getWeather( @PathVariable String city) {

		return getExternaApiWeather.getWeatherData( city);
	}


	@PostMapping("/cadastra")
	public ResponseEntity<Weather> saveWeatherForecast(@RequestBody List<Weather> weatherList) {

		List< Weather > dto = wfService.cadastrarDados( weatherList );
		if (dto.isEmpty()) {
			throw new IllegalArgumentException("A lista de previsões do tempo não pode estar vazia");
		}

		return new ResponseEntity<>(  HttpStatus.CREATED );
	}

}
