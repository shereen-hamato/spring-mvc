package com.shereen.catalog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.model.Item;
import com.shereen.catalog.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepo;

	public ItemService() {

	}

	public List<Item> getAllItemsByCatalog(Catalog cata) {
		List<Item> items = new ArrayList<>();
		itemRepo.findAllByCatalog(cata).forEach(item -> items.add(item));
		return items;
	}
	
	public Optional<Item> getItemByItemIdAndCatalog(Long id, Catalog catalog) {
		return itemRepo.findByIdAndCatalog(id, catalog);
	}

	public Optional<Item> getItemById(long id) {
		return itemRepo.findById(id);
	}

	public Item addItem(Item item) {
		return itemRepo.save(item);
	}
	
	public Item updateItem(Item item) {
		return itemRepo.save(item);
	}
	
	public void deleteItem(long id) {
		itemRepo.deleteById(id);
	}

}
