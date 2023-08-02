package br.com.weather.Forecast;

import br.com.weather.Forecast.base.ForecastApplication;
import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.service.WeatherForecastService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = ForecastApplication.class )
@RunWith( SpringRunner.class )
public class ForecastApplicationTests {

	@Autowired
	private WeatherForecastService service;
	@Test
	public void testSave() {

		Weather weather = new Weather();
		weather.setId( 100L);
		weather.setCity( "Ubatuba" );
		weather.setTemperature( 34.1 );

		List<Weather> list = Arrays.asList( weather);

		service.cadastrarDados(list);

	}

	@Test
	public void testUpdate(){


	}



}
