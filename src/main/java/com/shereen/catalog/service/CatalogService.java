package com.shereen.catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.repository.CatalogRepository;

@Service
public class CatalogService {
	
	private static List<Catalog> catalogs= new ArrayList<>();
	
	@Autowired
	CatalogRepository cataRepo;

	static {
		for (int i =1 ; i<= 10; i++) {
			Catalog cat = new Catalog(i , "catalog "+i , "catalog "+i+" desc");
			catalogs.add(cat);
		}
	}
	
	public List<Catalog> getAllCatalogs(){
		List<Catalog> cats = new ArrayList<>();
		cataRepo.findAll().forEach(cat->cats.add(cat));
		return cats;
	}
}
