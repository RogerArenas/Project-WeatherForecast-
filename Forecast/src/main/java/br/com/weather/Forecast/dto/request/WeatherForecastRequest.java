package br.com.weather.Forecast.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecastRequest {

	 private Long id;
	 private String city;
	 private LocalDate datePrevision;
	 private Integer temperature;
	 private Integer humidity;
	 private String moreInformation;
}
