package com.shereen.catalog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shereen.catalog.model.Catalog;

@Repository
public interface CatalogRepository extends CrudRepository<Catalog, Long> {
	
	
	

}
