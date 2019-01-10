package com.shereen.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shereen.catalog.model.Item;
import com.shereen.catalog.service.ItemService;

@Controller
@RequestMapping()
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("{cataId}/items")
	public String getAllItems(Model model, @PathVariable long cataId) {
		model.addAttribute("items", itemService.getAllItemsByCatalogId( cataId));
		return "item-list";
	}
	
	@GetMapping("{cataId}/items/JSON")
	@ResponseBody()
	public List<Item> getAllItemsJson(@PathVariable long cataId){
		return itemService.getAllItemsByCatalogId( cataId);
	}
	

}
