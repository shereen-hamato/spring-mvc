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

	public ItemService() {

	}

	public List<Item> getAllItemsByCatalogId(long cataId) {
		List<Item> items = new ArrayList<>();
		itemRepo.findAllByCatalogId(cataId).forEach(item -> items.add(item));
		return items;
	}

	public Item getItemBYID(long id) {
		// TODO: handle the exception
		return itemRepo.findById(id).get();
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
