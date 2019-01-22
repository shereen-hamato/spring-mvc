package com.shereen.catalog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
	public Iterable<Item> findAllByCatalog(Catalog catalog);

}
