package br.com.weather.Forecast.base;

import br.com.weather.Forecast.model.Weather;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

@ComponentScan(basePackages = {"br.com.weather"} )
@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.weather.Forecast.repository")
@EntityScan(basePackageClasses = { Weather.class })
@EnableOpenApi
@EnableWebMvc
public class ForecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForecastApplication.class, args);
	}

}
