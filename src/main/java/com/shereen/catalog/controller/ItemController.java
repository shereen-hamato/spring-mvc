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
import com.shereen.catalog.model.Item;
import com.shereen.catalog.service.CatalogService;
import com.shereen.catalog.service.ItemService;

@Controller
@RequestMapping("{cataId}/items")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	CatalogService catalogService;
	
	@GetMapping()
	public String getAllItems(Model model, @PathVariable long cataId) {
		Catalog  cata = catalogService.getCatalogById(cataId);
		model.addAttribute("items", itemService.getAllItemsByCatalog( cata));
		return "item-list";
	}
	
	@GetMapping("/JSON")
	@ResponseBody()
	public List<Item> getAllItemsJson(@PathVariable long cataId){
		Catalog  cata = catalogService.getCatalogById(cataId);
		return itemService.getAllItemsByCatalog( cata);
	}
	
	@GetMapping("/{id}")
	public String getItemByGuid(@PathVariable Long id, Model model, @ModelAttribute Catalog catalog) {
		model.addAttribute("item", itemService.getItemBYID(id));
		return "item-view";
	}

	@GetMapping("/new")
	public String getItemForm(@PathVariable Long cataId,Model model) {
		model.addAttribute("catalog", catalogService.getCatalogById(cataId));
		return "item-new";
	}
	
	@PostMapping("/new")
	public String addItem(@ModelAttribute Item item,@PathVariable Long cataId, Model model) {
		System.out.println(item);
		item.setCatalog(catalogService.getCatalogById(cataId));
		itemService.addItem(item);
		
		model.addAttribute("items", itemService.getAllItemsByCatalog(item.getCatalog()));
		return ("redirect:/{cataId}/items");
	}

	@GetMapping("/edit/{id}")
	public String geteditItemForm( @PathVariable Long id, Model model) {
		model.addAttribute("item", itemService.getItemBYID(id));
		return ("item-edit");

	}
	
	
	@PostMapping("/edit/{id}")
	public String editItem(@PathVariable Long cataId, @PathVariable Long id, @ModelAttribute Item item, Model model) {
		item.setCatalog(catalogService.getCatalogById(cataId));
		itemService.updateItem(item);
		model.addAttribute("items", itemService.getAllItemsByCatalog(item.getCatalog()));
		return ("redirect:/{cataId}/items");

	}

	@DeleteMapping("/{id}")
	public String deleteItem(@PathVariable Long id, Model model) {
		Catalog cataId= itemService.getItemBYID(id).getCatalog();
		itemService.deleteItem(id);
		model.addAttribute("items", itemService.getAllItemsByCatalog(cataId));
		return ("redirect:catalog-list");

	}
	

}
