package com.shereen.catalog.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shereen.catalog.exception.ResourceNotFoundException;
import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.service.CatalogService;
import com.shereen.catalog.service.StorageService;

@Controller
@RequestMapping("/catalogs")
public class CatalogController {

	@Autowired
	private CatalogService cataService;
	
	@Autowired
	private StorageService storageService;

	@GetMapping
	public String getCatalog(Model model) {
		model.addAttribute("catalogs", cataService.getAllCatalogs());
		return "catalog-list";
	}

	@GetMapping("/JSON")
	@ResponseBody
	public List<Catalog> getAllCatalogJson() {
		return cataService.getAllCatalogs();
	}

	@GetMapping("/{id}")
	public String getCatalogByGuid(@PathVariable Long id, Model model) {
		Optional<Catalog> cata = cataService.getCatalogById(id);
		if (!cata.isPresent())
			throw new ResourceNotFoundException();
		model.addAttribute("catalog", cataService.getCatalogById(id));
		return "catalog-view";
	}
	
	@GetMapping("/new")
	public String getAddCatalogForm() {
		return "catalog-new";
	}

	@PostMapping("/new")
	public String addCatalog(@RequestParam("file") MultipartFile file, @ModelAttribute Catalog catalog, Model model) throws IOException {
		
	
        storageService.storeFile(file);
        catalog.setImagePath("http://localhost:8080/files/"+file.getOriginalFilename());
		cataService.addCatalog(catalog);
		model.addAttribute("catalogs", cataService.getAllCatalogs());
		return ("redirect:/catalogs");
	}

	
	@GetMapping("/edit/{id}")
	public String getEditCatalogForm(Model model, @PathVariable Long id) {
		Optional<Catalog> cata = cataService.getCatalogById(id);
		if (!cata.isPresent())
			throw new ResourceNotFoundException();
		model.addAttribute("catalog", cataService.getCatalogById(id).get());
		return "catalog-edit";
	}
	@PostMapping("/edit")
	public String editCatalog(@ModelAttribute Catalog catalog, Model model) {
		cataService.updateCatalg(catalog);
		model.addAttribute("catalogs", cataService.getAllCatalogs());
		return ("redirect:/catalogs");

	}

	@GetMapping("/delete/{id}")
	public String getdeleteCatalogForm(Model model, @PathVariable Long id) {
		Optional<Catalog> cata = cataService.getCatalogById(id);
		if (!cata.isPresent())
			throw new ResourceNotFoundException();
		model.addAttribute("catalog", cataService.getCatalogById(id).get());
		return "catalog-delete";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteCatalog(@PathVariable Long id, Model model) {
		Optional<Catalog> cata = cataService.getCatalogById(id);
		if (!cata.isPresent())
			throw new ResourceNotFoundException();
		cataService.deleteCatalog(id);
		model.addAttribute("catalogs", cataService.getAllCatalogs());
		return ("redirect:/catalogs");

	}
	
	

}
