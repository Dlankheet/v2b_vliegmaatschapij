package nl.hu.bep2.vliegmaatschappij.domein;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TravelClass {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String name;
	@NotNull
	@Positive
	private int maxSeats;
	@OneToMany(mappedBy="travelClass")
	@JsonIgnore
	@ToString.Exclude
	private List<TravelClassFlight> travelClassFlightList;
	@JsonIgnore
	@ManyToOne
	private Planetype planeType;
	private double pricePerKm;
}