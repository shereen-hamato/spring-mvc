package com.shereen.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shereen.catalog.model.Catalog;
import com.shereen.catalog.model.Item;
import com.shereen.catalog.service.ItemService;

@Controller
@RequestMapping("{cataId}/items")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping()
	public String getAllItems(Model model, @PathVariable long cataId) {
		model.addAttribute("items", itemService.getAllItemsByCatalogId( cataId));
		return "item-list";
	}
	
	@GetMapping("/JSON")
	@ResponseBody()
	public List<Item> getAllItemsJson(@PathVariable long cataId){
		return itemService.getAllItemsByCatalogId( cataId);
	}
	
	@GetMapping("/{id}")
	public String getItemByGuid(@PathVariable Long id, Model model) {
		model.addAttribute("item", itemService.getItemBYID(id));
		return "item-view";
	}

	@PostMapping()
	public String addItem(@RequestBody Item item, Model model) {
		itemService.addItem(item);
		model.addAttribute("items", itemService.getAllItemsByCatalogId(item.getCatalogId()));
		return ("redirect:item-list");
	}

	@PutMapping()
	public String editItem(@RequestBody Item item, Model model) {
		itemService.addItem(item);
		model.addAttribute("items", itemService.getAllItemsByCatalogId(item.getCatalogId()));
		return ("redirect:item-list");

	}

	@DeleteMapping("/{id}")
	public String deleteItem(@PathVariable Long id, Model model) {
		long cataId= itemService.getItemBYID(id).getCatalogId();
		itemService.deleteItem(id);
		model.addAttribute("items", itemService.getAllItemsByCatalogId(cataId));
		return ("redirect:catalog-list");

	}
	

}
