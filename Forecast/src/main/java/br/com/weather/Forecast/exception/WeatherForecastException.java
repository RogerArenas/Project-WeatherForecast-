package br.com.weather.Forecast.exception;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.nio.charset.Charset;

@Getter
public class WeatherForecastException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = -3338125523245584585L;

	private final HttpStatus status;
	private final String statusText;
	private final String message;
	private final HttpHeaders httpHeaders;
	private final byte[] body;
	private final transient Charset charset;

	public WeatherForecastException( HttpStatus status, String statusText, String message,
									 @Nullable HttpHeaders httpHeaders, @Nullable byte[] body, @Nullable  Charset charset ) {
		this.status = status;
		this.statusText = statusText;

		this.message = message;
		this.httpHeaders = httpHeaders;
		this.body = body;
		this.charset = charset;
	}
}
