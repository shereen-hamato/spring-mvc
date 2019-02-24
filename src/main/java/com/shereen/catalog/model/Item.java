package com.shereen.catalog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="ITEM")
public class Item {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message="Item name should not be null!")
	@Size(min=3, max=20, message="name length should be between 2 and 20 character!")
	@Column(name="NAME")
	private String name;
	
	@NotNull(message="Item description should not be null!")
	@Size(min=3, max=225, message="description length should be between 2 and 20 character!")
	@Column(name= "DESCRIPTION")
	private String description;
	
	@Column(name="PRICE")
	private double price;
	
	@Column(name="DURATION")
	private String duration;
	
	@Column(name="IMAGE_PATH")
	private String image_path;
	
	@ManyToOne
	@JoinColumn(name="ITEM_CATALOG_ID", nullable=false)
	private Catalog catalog;
	
	public Item() {
		super();
	}

	public Item(long id, String name, String description, double price, String duration, String imagePath, Catalog catalog) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.duration = duration;
		this.image_path= imagePath;
		this.catalog = catalog;
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

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", duration="
				+ duration + ", image_path=" + image_path + ", catalog=" + catalog + "]";
	}


	

}
