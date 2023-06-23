package br.com.weather.Forecast.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
@Data
@EqualsAndHashCode( callSuper = false )
public class WeatherForecastDto {

	private Long      id;
	private String    city;
	private LocalDate datePrevision;
	private Double    temperature;
	private Double    humidity;
	private String    moreInformation;
}
