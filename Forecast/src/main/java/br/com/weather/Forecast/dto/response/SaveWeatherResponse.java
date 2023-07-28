package br.com.weather.Forecast.dto.response;

import br.com.weather.Forecast.model.Weather;
import lombok.Data;

import java.util.List;
@Data
public class SaveWeatherResponse {

	private String          message;
	private List< Weather > savedWeather;
	private Weather updatedWeatherResponse ;

	public SaveWeatherResponse( String message, List< Weather > savedWeather ) {
		this.message      = message;
		this.savedWeather = savedWeather;
	}

	public SaveWeatherResponse( String message, Weather updatedWeatherResponse ) {
		this.message                = message;
		this.updatedWeatherResponse = updatedWeatherResponse;
	}
}
