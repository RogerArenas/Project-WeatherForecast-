package br.com.weather.Forecast.model;

import br.com.weather.Forecast.dto.WeatherForecastDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_WEATHER")
public class Weather implements Serializable {


	private static final long serialVersionUID = -6161833244219639141L;
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", nullable = false )
	private Long      id;
	private String    city;
	@Column(name = "datePrevision")
	private LocalDate datePrevision;
	private Double    temperature;
	private Double    humidity;
	private String    moreInformation;

	public static WeatherForecastDto create( Weather w ){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(w, WeatherForecastDto.class);

	}
}
