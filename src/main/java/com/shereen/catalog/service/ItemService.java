package com.shereen.catalog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.model.Item;

@Service
public class ItemService {

	
	private CatalogService catalogService;

	private Map<Long , List<Item>> items = new HashMap<>();

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
		List<Item> itemList = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			Item item = new Item(i, "item " + i, "description: item for catalog " + cata.getId(), 1.1, "duration " + i + " month", cata.getId());
			itemList.add(item);
		}
		items.put(cata.getId(), itemList);
	}

	public List<Item> getAllItems(long cataId) {
		
		return items.get(cataId);
	}

}
