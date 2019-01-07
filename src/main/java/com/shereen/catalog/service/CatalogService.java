package com.shereen.catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shereen.catalog.model.Catalog;

@Service
public class CatalogService {
	
	private static List<Catalog> catalogs= new ArrayList<>();
	
	

	static {
		for (int i =0 ; i<= 10; i++) {
			Catalog cat = new Catalog(i , "catalog "+i , "catalog "+i+" desc");
			catalogs.add(cat);
		}
	}
	
	public List<Catalog> getAllCatalogs(){
		return catalogs;
	}
}
