package com.shereen.catalog.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "ITEM_CATALOG")
public class Catalog {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="IMAGE_PATH")
	private String imagePath;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ITEM_CATALOG_ID")
	private List<Item> items;

	public Catalog() {
		super();
	}
	
	
	public Catalog(long id, String name, String description, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imagePath=imagePath;
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

	
	
	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	@Override
	public String toString() {
		return "Catalog [id=" + id + ", name=" + name + ", description=" + description + ", imagePath=" + imagePath
				+ ", items=" + items + "]";
	}





}
