package com.example.demo.Entity;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name="transportmode")
public class Transportmode {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mode_generator")
	private int id;
	private String name;
	private String mode;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "traveller_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Traveller traveller;

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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Traveller getTraveller() {
		return traveller;
	}

	
	public Transportmode(int id, String name, String mode, Traveller traveller) {
		super();
		this.id = id;
		this.name = name;
		this.mode = mode;
		this.traveller = traveller;
	}

	public void setTraveller(Traveller traveller) {
		this.traveller = traveller;
	}

	public Transportmode() {
		super();
	}
	
	
}