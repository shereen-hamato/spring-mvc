package com.shereen.catalog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.model.Item;
import com.shereen.catalog.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepo;
	
	private CatalogService catalogService;

	public ItemService( CatalogService catalogService) {

	}

	public List<Item> getAllItems(long cataId) {
		List<Item>items= new ArrayList<>();
		itemRepo.findAllByCatalogId(cataId).forEach(item->items.add(item));
		return items;
	}

}
