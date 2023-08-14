package br.com.weather.Forecast;

import br.com.weather.Forecast.base.ForecastApplication;
import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.service.WeatherForecastService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

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
		 service.cadastrarDados( list);
	}

	@Test
	public void testSaveAndDelete() {

		Weather weather = new Weather();
		weather.setId( 1L );
		weather.setCity( "Indaiatuba" );
		weather.setHumidity( 80.0 );
		weather.setTemperature( 33.2 );
		weather.setMoreInformation( "Calor extremo" );

		List<Weather> weathers = List.of( weather );

		List <Weather> savedList = service.cadastrarDados( ( weathers ));

		assertNotNull(savedList);

		List<Weather> resultWeather = Collections.singletonList( savedList.get( 0 ) );
		Long id = resultWeather.get( 0 ).getId();
		assertNotNull( id );

		Optional<Weather> optional = service.findById( id );
		assertTrue( optional.isPresent() );

		weather = optional.get();
		assertEquals( "Indaiatuba" ,weather.getCity());
		assertEquals( Optional.of( 80.0 ), weather.getHumidity( ));
		assertEquals( Optional.of( 33.2 ), weather.getTemperature() );
		assertEquals( "Calor extremo",weather.getMoreInformation() );

		service.deleteById( id );

		assertFalse( service.findById( id ).isPresent() );

	}

	@Test
	public void testUpdate(){

       Weather  weather = new Weather();
	   weather.setId( 100L );
	   weather.setCity( "Leme" );

	}



}
