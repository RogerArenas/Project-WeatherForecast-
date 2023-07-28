package br.com.weather.Forecast.controller;

import br.com.weather.Forecast.domain.WeatherForecastDomain;
import br.com.weather.Forecast.dto.WeatherForecastDto;
import br.com.weather.Forecast.dto.response.SaveWeatherResponse;
import br.com.weather.Forecast.exception.NotFoundException;
import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.service.GetExternaApiWeather;
import br.com.weather.Forecast.service.WeatherForecastService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

	@GetMapping("/{city}/{datePrevision}")
	@ApiOperation(value = "Retorna uma cidade e a data")
	public ResponseEntity<WeatherForecastDto> getWeatherForecast(
			@PathVariable String city,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate datePrevision) {

		WeatherForecastDto dto = weatherForecastDomain.get(city, datePrevision);
		if (dto == null) {
			return ResponseEntity.notFound().build();
		} else {
			log.info("Consulta dados banco: {}", dto);
			return ResponseEntity.ok(dto);
		}
	}

	@GetMapping
	@ApiOperation( value = "Retorna todos registro do banco")
	public ResponseEntity<List<Weather>> getWeatherForecastAll() {
		List< Weather > weather = weatherForecastDomain.getAll( );
		if ( weather.isEmpty( ) ) {
			log.info( "Sem dados cadastrados no banco" );
			return ResponseEntity.notFound( ).build( );
		}

		log.info( "Dados consultados do banco: {}", weather );
		return ResponseEntity.ok( weather );
	}

	@GetMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation( value = "Retorna apenas um cidade")
	public ResponseEntity< Weather > getWeather( @PathVariable String city) {
         Weather weathers = weatherForecastDomain.getByCity( city );
		 if(weathers == null){
			 log.info( "Cidade não cadastrada no banco de dados" );
			 return ResponseEntity.notFound( ).build( );
		 }
		log.info( "Dados consultados do banco: {}", weathers );
		return ResponseEntity.ok( weathers );
	}


	@PostMapping(value ="/cadastra", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Metodo para cadastrar uma previsão")
	public ResponseEntity< SaveWeatherResponse > saveWeatherForecast( @RequestBody List<Weather> weatherList) {

		if (weatherList.isEmpty()) {
			throw new IllegalArgumentException("A lista de previsões do tempo não pode estar vazia");
		}

		List< Weather > savedweather = wfService.cadastrarDados( weatherList );

		String message = "Previsões do tempo salvas com sucesso!!";

		SaveWeatherResponse response = new SaveWeatherResponse( message, savedweather );

		return ResponseEntity.status( HttpStatus.CREATED ).body( response );

	}


	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Metodo para deletar uma previsão")
	public ResponseEntity<Void> deleteWeather(@PathVariable Long id) {
		try {
			Optional< Weather > deletedWeather = weatherForecastDomain.deleteById( id );
			if ( deletedWeather.isEmpty( ) ) {
				log.error("Dado não encontrado no banco de dados.");
				return ResponseEntity.notFound().build();
			}
             //TODO melhorias - Retornar registro deletado com mensagem amigavel
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			log.error("Erro ao excluir o registro: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}



	@PutMapping("/{id}")
	@ApiOperation(value = "Metodo para atualizar uma previsão")
	public ResponseEntity< SaveWeatherResponse > alterWeather( @PathVariable("id") Long id, @RequestBody Weather weather  ){
    try {
		// Verifica se o objeto weather é nulo
		if (weather == null) {
			return ResponseEntity.badRequest().build();
		}
		// Buscar o registro no banco de dados
		Weather updatedWeatherData = weatherForecastDomain.update(id, weather);

		if (updatedWeatherData == null) {
			return ResponseEntity.notFound().build();
		}
		String message = "Dados atualizados com sucesso";

		SaveWeatherResponse response = new SaveWeatherResponse( message, updatedWeatherData );


		return ResponseEntity.ok(response);
	}catch ( NotFoundException e ){
		throw new NotFoundException( "Erro ao atualizar registro" + e.getMessage());
	}


	}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             

}
