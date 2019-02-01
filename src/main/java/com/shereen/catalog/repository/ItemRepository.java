package com.shereen.catalog.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
	public Iterable<Item> findAllByCatalog(Catalog catalog);
	
	public Optional<Item> findByIdAndCatalog(Long id, Catalog catalog);

}
