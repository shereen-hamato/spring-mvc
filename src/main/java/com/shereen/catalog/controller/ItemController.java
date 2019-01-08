package com.shereen.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shereen.catalog.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping
	public String getAllItems(Model model) {
		model.addAttribute("items", itemService.getAllItems());
		return "item-list";
	}
	

}
