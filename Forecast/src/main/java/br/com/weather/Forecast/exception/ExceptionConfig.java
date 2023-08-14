package br.com.weather.Forecast.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConfig {

	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity errorNotFound(Exception e){
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity errorBadRequest(Exception e){
		return ResponseEntity.notFound().build();
	}
}
