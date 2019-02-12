package com.shereen.catalog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shereen.catalog.model.Catalog;

@Repository
public interface CatalogRepository extends PagingAndSortingRepository<Catalog, Long> {
	
	
	

}
