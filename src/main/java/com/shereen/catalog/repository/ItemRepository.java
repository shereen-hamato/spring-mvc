package com.shereen.catalog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.model.Item;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
	
	public Page<Item> findAllByCatalog(Pageable page,Catalog catalog);
	
	public Optional<Item> findByIdAndCatalog(Long id, Catalog catalog);

}
