package br.com.weather.Forecast.service;

import br.com.weather.Forecast.dto.response.WeatherResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
@Log4j2
@Component
public class GetExternaApiWeather {

	private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

	private static final String API_KEY = "947594870c0aae39832dfe25318d6c5d"; // Substitua pelo sua chave de API

	@Autowired
	private RestTemplate restTemplate;



	public WeatherResponse getWeatherData(String city) {
		// Configurar os cabeçalhos da requisição
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Montar a URL da chamada à API
		String urlBuilder = API_URL + "?q=" + city + "&appid=" + API_KEY;
		log.info("Received Data for call api weather: {}", urlBuilder);

		// Fazer a chamada à API
		ResponseEntity<WeatherResponse> responseEntity = restTemplate.getForEntity(urlBuilder, WeatherResponse.class);
		WeatherResponse response = responseEntity.getBody();
		log.info("Received response for call api weather: {}", response);

		// Processar a resposta da API
		// Aqui você pode fazer o que quiser com os dados retornados pela API
		// Por exemplo, imprimir a temperatura atual da cidade
		if (response == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Dados retornando estão nullos");
		}


		log.info("Dados retornados na API extena", response);

		return response;
	}


}
