package com.shereen.catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.repository.CatalogRepository;

@Service
@Transactional
public class CatalogService {

	@Autowired
	CatalogRepository cataRepo;

	public List<Catalog> getAllCatalogs() {
		List<Catalog> cats = new ArrayList<>();
		cataRepo.findAll().forEach(cat -> cats.add(cat));
		return cats;
	}

	public Catalog getCatalogById(long id) {
		// TODO: handle the null exception
		return cataRepo.findById(id).get();
	}

	public Catalog addCatalog(Catalog cata) {
		return cataRepo.save(cata);
	}

	public Catalog updateCatalg(Catalog cata) {
		return cataRepo.save(cata);
	}

	public void deleteCatalog(long id) {
		cataRepo.deleteById(id);
	}
}
