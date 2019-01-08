package com.shereen.catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.model.Item;

@Service
public class ItemService {

	
	private CatalogService catalogService;

	private List<Item> items = new ArrayList<>();

	@Autowired
	public ItemService( CatalogService catalogService) {
		super();
		this.catalogService= catalogService;
		initialize();
	}

	private void initialize() {
		catalogService.getAllCatalogs().forEach(cata -> createItem(cata));

	}

	private void createItem(Catalog cata) {
		for (int i = 0; i < 4; i++) {
			Item item = new Item(i, "item " + i, "description " + i, 1.1, "duration " + i + " month", cata.getId());
			items.add(item);
		}
	}

	public List<Item> getAllItems() {
		
		return items;
	}

}
