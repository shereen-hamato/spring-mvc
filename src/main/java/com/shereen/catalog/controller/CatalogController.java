package com.shereen.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.service.CatalogService;

@Controller
@RequestMapping("/catalogs")
public class CatalogController {

	@Autowired
	private CatalogService cataService;

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
		model.addAttribute("catalog", cataService.getCatalogById(id));
		return "catalog-view";
	}
	
	@GetMapping("/new")
	public String getAddCatalogForm() {
		return "catalog-new";
	}

	@PostMapping("/new")
	public String addCatalog(@ModelAttribute Catalog catalog, Model model) {
		cataService.addCatalog(catalog);
		model.addAttribute("catalogs", cataService.getAllCatalogs());
		return ("redirect:/catalogs");
	}

	
	@GetMapping("/edit/{id}")
	public String getEditCatalogForm(Model model, @PathVariable Long id) {
		model.addAttribute("catalog", cataService.getCatalogById(id));
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
		model.addAttribute("catalog", cataService.getCatalogById(id));
		return "catalog-delete";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteCatalog(@PathVariable Long id, Model model) {
		cataService.deleteCatalog(id);
		model.addAttribute("catalogs", cataService.getAllCatalogs());
		return ("redirect:/catalogs");

	}

}
