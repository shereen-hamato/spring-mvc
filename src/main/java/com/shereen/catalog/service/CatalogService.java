package com.shereen.catalog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.repository.CatalogRepository;

@Service
@Transactional
public class CatalogService {

	@Autowired
	CatalogRepository cataRepo;

	public Page<Catalog> getAllCatalogs(Integer pageNum, Integer pageSize) {
		Page<Catalog> cats =
		cataRepo.findAll( PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "id")));
		return cats;
	}

	public Optional<Catalog> getCatalogById(long id) {
		return cataRepo.findById(id);
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
