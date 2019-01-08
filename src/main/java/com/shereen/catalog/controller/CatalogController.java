package com.shereen.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Catalog> getAllCatalogJson(){
		return cataService.getAllCatalogs();
	}

}
