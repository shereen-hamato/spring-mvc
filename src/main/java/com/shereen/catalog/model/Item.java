package com.shereen.catalog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name= "DESCRIPTION")
	private String description;
	
	@Column(name="PRICE")
	private double price;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="CATALOG_ID")
	private long catalog_id;
	
	public Item() {
		super();
	}

	public Item(long id, String name, String description, double price, String duration, long catalog_id) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.duration = duration;
		this.catalog_id = catalog_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public long getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(long catalog_id) {
		this.catalog_id = catalog_id;
	}
	
	

}
