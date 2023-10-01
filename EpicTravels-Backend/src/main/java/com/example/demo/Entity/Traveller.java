package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="traveller")
public class Traveller {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
    private String name;
    private String arrival;
    private String departure;
    private int budget;
    private int age;
  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public Traveller(int id, String name, String arrival, String departure, int budget, int age) {
		super();
		this.id = id;
		this.name = name;
		this.arrival = arrival;
		this.departure = departure;
		this.budget = budget;
		this.age = age;
	}
	public Traveller() {
		super();
	}
}
