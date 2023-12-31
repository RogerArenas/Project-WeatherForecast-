package br.com.weather.Forecast.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;


import java.nio.charset.Charset;

public class NotFoundException extends WeatherForecastException{

	private static final long serialVersionUID = -8130663504365147192L;

	public NotFoundException( String message ) {
		super(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.getReasonPhrase(),message,new HttpHeaders(),"".getBytes(  ), Charset.defaultCharset());
	}
}
