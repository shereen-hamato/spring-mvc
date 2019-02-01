package com.shereen.catalog.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.shereen.catalog.exception.ResourceNotFoundException;
import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.model.Item;
import com.shereen.catalog.service.CatalogService;
import com.shereen.catalog.service.ItemService;

@Controller
@RequestMapping()
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	CatalogService catalogService;
	
	@GetMapping("{cataId}/items")
	public String getAllItems(Model model, @PathVariable long cataId) {
		Catalog  cata = catalogService.getCatalogById(cataId);
		model.addAttribute("items", itemService.getAllItemsByCatalog( cata));
		model.addAttribute("catalog", cata);
		return "item-list";
	}
	
	@GetMapping("/JSON/{cataId}/items")
	@ResponseBody()
	public List<Item> getAllItemsJson(@PathVariable long cataId){
		Catalog  cata = catalogService.getCatalogById(cataId);
		return itemService.getAllItemsByCatalog( cata);
	}
	
	@GetMapping("{cataId}/items/{id}")
	public String getItemByGuid(@PathVariable Long id, Model model, @PathVariable Long cataId) {
		Catalog catalog = catalogService.getCatalogById(cataId);
		Optional<Item> item = itemService.getItemByItemIdAndCatalog(id, catalog);
		
		if (!item.isPresent()) throw new ResourceNotFoundException();
		
		model.addAttribute("item", item.get() );
		model.addAttribute("catalog", catalog);
		return "item-view";
	}

	@GetMapping("{cataId}/items/new")
	public String getItemForm(@PathVariable Long cataId,Model model) {
		model.addAttribute("catalog", catalogService.getCatalogById(cataId));
		return "item-new";
	}
	
	@PostMapping("{cataId}/items/new")
	public String addItem(@ModelAttribute Item item,@PathVariable Long cataId, Model model) {
		System.out.println(item);
		item.setCatalog(catalogService.getCatalogById(cataId));
		itemService.addItem(item);
		
		model.addAttribute("items", itemService.getAllItemsByCatalog(item.getCatalog()));
		return ("redirect:/{cataId}/items");
	}

	@GetMapping("{cataId}/items/edit/{id}")
	public String geteditItemForm( @PathVariable Long id, Model model) {
		model.addAttribute("item", itemService.getItemById(id));
		return ("item-edit");

	}
	
	
	@PostMapping("{cataId}/items/edit/{id}")
	public String editItem(@PathVariable Long cataId, @PathVariable Long id, @ModelAttribute Item item, Model model) {
		item.setCatalog(catalogService.getCatalogById(cataId));
		itemService.updateItem(item);
		model.addAttribute("items", itemService.getAllItemsByCatalog(item.getCatalog()));
		return ("redirect:/{cataId}/items");

	}
	
	@GetMapping("{cataId}/items/delete/{id}")
	public String getDeleteItemForm( @PathVariable Long id, Model model) {
		model.addAttribute("item", itemService.getItemById(id));
		return ("item-delete");

	}

	@PostMapping("{cataId}/items/delete/{id}")
	public String deleteItem(@PathVariable Long cataId, @PathVariable Long id, Model model) {
		Catalog cata= catalogService.getCatalogById(cataId);
		itemService.deleteItem(id);
		model.addAttribute("items", itemService.getAllItemsByCatalog(cata));
		return ("redirect:/{cataId}/items");

	}
	

}
