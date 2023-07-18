package br.com.weather.Forecast.repository;

import br.com.weather.Forecast.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
@Repository
public interface WeatherRepository extends JpaRepository< Weather, Long> {


	Optional< Weather> findByCity(String city );
}

