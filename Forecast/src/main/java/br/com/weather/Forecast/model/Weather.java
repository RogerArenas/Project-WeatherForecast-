package br.com.weather.Forecast.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_WEATHER")
public class Weather implements Serializable {

	@Serial
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

	public Weather( Optional< Weather> updatedWeather, String s ) {
	}
}
