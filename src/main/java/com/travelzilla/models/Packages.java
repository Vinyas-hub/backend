package com.travelzilla.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
//import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

@Entity
public class Packages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer packageId;

	@NotNull(message = "Package Name Cannot Be Null!")
	//@Size(min = 10, max = 100, message = "Package Name Length must be between 10 to 100 characters.")

	private String packageName;

	@NotNull(message = "Package Description Cannot Be Null!")
	//@Size(min = 10, max = 1000, message = "Package Description Length must be between 10 to 1000 characters.")


	private String packageDescription;


	@JsonIgnore
	private PackageStatus packageStatus = PackageStatus.AVAILABLE;

	private String packageRating = "Package Not Rated Yet";

	@NotNull(message = "Route Cannot Be Null!")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "routeId")
	@JsonIgnore
	private Route route;

	@NotNull(message = "Hotel Cannot Be Null!")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_Id")
	@JsonIgnore
	private Hotel hotel;

	@NotNull(message = "Bus Cannot be Null")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "busId")
	@JsonIgnore
	private Bus bus;

	@NotNull(message = "Package Cost Cannot be Null")
	//@Min(value = 2000, message = "Package Cost Cannot be Less Than 2000.")
	private Double packageCost;

//	@NotNull(message =  "Capacity cannot be null")
	private Integer packageCapacity;


	@NotNull(message = "Capacity cannot be null")
	private Integer currentAvailability;

	public Packages() {
		// TODO Auto-generated constructor stub
	}

	public Packages(Integer packageId, String packageName, String packageDescription, Route route, Hotel hotel, Bus bus,
			Double packageCost) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageDescription = packageDescription;
		this.route = route;
		this.hotel = hotel;
		this.bus = bus;
		this.packageCost = packageCost;

	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public PackageStatus getPackageStatus() {
		return packageStatus;
	}

	public String getPackageRating() {
		return packageRating;
	}

	public void setPackageRating(String packageRating) {
		this.packageRating = packageRating;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Double getPackageCost() {
		return packageCost;
	}

	public void setPackageCost(Double packageCost) {
		this.packageCost = packageCost;
	}

	public Integer getCurrentAvailability() {
		return currentAvailability;
	}

	public void setCurrentAvailability(Integer currentAvailability) {
		this.currentAvailability = currentAvailability;
	}

	public void setPackageStatus(Integer noOfPerson) {

		currentAvailability = currentAvailability - noOfPerson;
		
		if (currentAvailability <= 0 || hotel.getHotelStatus() == HotelStatus.SOLD_OUT) {

			packageStatus = PackageStatus.SOLD_OUT;
		} else {
			packageStatus = PackageStatus.AVAILABLE;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentAvailability, packageCost, packageDescription, packageId, packageName, packageRating,
				packageStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Packages other = (Packages) obj;
		return Objects.equals(currentAvailability, other.currentAvailability)
				&& Objects.equals(packageCost, other.packageCost)
				&& Objects.equals(packageDescription, other.packageDescription)
				&& Objects.equals(packageId, other.packageId) && Objects.equals(packageName, other.packageName)
				&& Objects.equals(packageRating, other.packageRating) && packageStatus == other.packageStatus;
	}

	

}
