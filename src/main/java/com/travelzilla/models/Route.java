package com.travelzilla.models;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
//import java.util.List;
import java.util.Objects;
import java.util.Set;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer routeId;
	@NotNull(message = "Route From Cannot Be Null!")
	private String routeFrom;
	@NotNull(message = "Route To Cannot Be Null!")
	private String routeTo;
	
	//Use BusTiming class for neat and clean code.
	//@NotNull(message = "Time Cannot Be Null!")
	@Embedded
	private BusTiming time;
	@NotNull(message = "pickupPoint Cannot Be Null!")
	private String pickupPoint;
	@NotNull(message = "Fare Cannot Be Null!")
	private double fare;
	

	// A route have many packages.
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "route")
	private Set<Packages> packageList= new HashSet<>();
	
	// A route have many Buses.
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "route")
	private Set<Bus> busList= new HashSet<>();

	
	public Route() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Route(Integer routeId, String routeFrom, String routeTo, BusTiming time, String pickupPoint, double fare,
			Set<Packages> packageList, Set<Bus> busList) {
		super();
		this.routeId = routeId;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.time = time;
		this.pickupPoint = pickupPoint;
		this.fare = fare;
		this.packageList = packageList;
		this.busList = busList;
	}

	public Integer getRouteId() {
		return routeId;
	}
  public void setRouteId(Integer routeId) {
	this.routeId = routeId;
}

	public String getRouteFrom() {
		return routeFrom;
	}

	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}

	public String getRouteTo() {
		return routeTo;
	}

	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}

	public BusTiming getTime() {
		return time;
	}

	public void setTime(BusTiming time) {
		this.time = time;
	}

	public String getPickupPoint() {
		return pickupPoint;
	}

	public void setPickupPoint(String pickupPoint) {
		this.pickupPoint = pickupPoint;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public Set<Packages> getPackageList() {
		return packageList;
	}

	public void setPackageList(Set<Packages> packageList) {
		this.packageList = packageList;
	}

	public Set<Bus> getBusList() {
		return busList;
	}

	public void setBusList(Set<Bus> busList) {
		this.busList = busList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fare, pickupPoint, routeFrom, routeId, routeTo, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		return Double.doubleToLongBits(fare) == Double.doubleToLongBits(other.fare)
				&& Objects.equals(pickupPoint, other.pickupPoint) && Objects.equals(routeFrom, other.routeFrom)
				&& Objects.equals(routeId, other.routeId) && Objects.equals(routeTo, other.routeTo)
				&& Objects.equals(time, other.time);
	}

	

	
	
	


	

	
}

