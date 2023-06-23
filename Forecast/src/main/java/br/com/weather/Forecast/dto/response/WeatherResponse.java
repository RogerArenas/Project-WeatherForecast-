package br.com.weather.Forecast.dto.response;

import br.com.weather.Forecast.model.Weather;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {

	@JsonProperty("main")
	private MainWeatherData main;
	@JsonProperty("coord")
	private Coord coord;

	private List< Weather > weather;
	private String base;
	private int    visibility;
	private Wind wind;
	private Clouds clouds;
	private long dt;
	private Sys sys;
	private int timezone;
	private long id;
	private String name;
	private int cod;

	public WeatherResponse( String dadosSavosComSucesso, Weather dto ) {

	}

	@Data
	public static class MainWeatherData {
		private double temp;
		private double feels_like;
		private double temp_min;
		private double temp_max;
		private int pressure;
		private int humidity;
	}

}
