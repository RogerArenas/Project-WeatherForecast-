package br.com.weather.Forecast.base;

import br.com.weather.Forecast.model.Weather;
import br.com.weather.Forecast.repository.WeatherRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"br.com.weather"} )
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = { WeatherRepository.class })
@EntityScan(basePackageClasses = { Weather.class })
public class ForecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForecastApplication.class, args);
	}

}
