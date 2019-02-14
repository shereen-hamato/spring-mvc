package com.shereen.catalog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private final Integer pageSize = 20;

	@GetMapping("{cataId}/items")
	public String getAllItems(Model model, @PathVariable long cataId, @RequestParam("page") Integer page) {
		Optional<Catalog> cata = catalogService.getCatalogById(cataId);
		if (!cata.isPresent())
			throw new ResourceNotFoundException();
		model.addAttribute("items", itemService.getAllItemsByCatalog(cata.get(), page, pageSize));
		model.addAttribute("catalog", cata.get());
		return "item-list";
	}

	@GetMapping("/JSON/{cataId}/items")
	@ResponseBody()
	public Page<Item> getAllItemsJson(@PathVariable long cataId, @RequestParam("page") Integer page) {
		Optional<Catalog> cata = catalogService.getCatalogById(cataId);
		if (!cata.isPresent())
			throw new ResourceNotFoundException();
		return itemService.getAllItemsByCatalog(cata.get(), page, pageSize);
	}

	@GetMapping("{cataId}/items/{id}")
	public String getItemByGuid(@PathVariable Long id, Model model, @PathVariable Long cataId) {
		Optional<Catalog> catalog = catalogService.getCatalogById(cataId);
		Optional<Item> item = itemService.getItemByItemIdAndCatalog(id, catalog.get());

		if (!item.isPresent())
			throw new ResourceNotFoundException();

		model.addAttribute("item", item.get());
		model.addAttribute("catalog", catalog.get());
		return "item-view";
	}

	@GetMapping("{cataId}/items/new")
	public String getItemForm(@PathVariable Long cataId, Model model) {
		model.addAttribute("catalog", catalogService.getCatalogById(cataId));
		return "item-new";
	}

	@PostMapping("{cataId}/items/new")
	public String addItem(@ModelAttribute Item item, @PathVariable Long cataId, Model model) {
		Optional<Catalog> catalog = catalogService.getCatalogById(cataId);
		if (!catalog.isPresent())
			throw new ResourceNotFoundException();
		item.setCatalog(catalog.get());
		itemService.addItem(item);

		model.addAttribute("items", itemService.getAllItemsByCatalog(item.getCatalog(), 0, pageSize));
		return ("redirect:/{cataId}/items");
	}

	@GetMapping("{cataId}/items/edit/{id}")
	public String geteditItemForm( @PathVariable Long cataId, @PathVariable Long id, Model model) {
		Optional<Catalog> catalog = catalogService.getCatalogById(cataId);
		if (!catalog.isPresent())
			throw new ResourceNotFoundException();
		Optional<Item>item = itemService.getItemByItemIdAndCatalog(id, catalog.get());
		if (!item.isPresent())
			throw new ResourceNotFoundException();
		
		model.addAttribute("item", item.get());
		return ("item-edit");

	}

	@PostMapping("{cataId}/items/edit/{id}")
	public String editItem(@PathVariable Long cataId, @PathVariable Long id,@RequestParam("page") Integer page, @ModelAttribute Item item, Model model) {
		if (!itemService.getItemById(id).isPresent())
			throw new ResourceNotFoundException();
		Optional<Catalog> catalog = catalogService.getCatalogById(cataId);
		if (!catalog.isPresent())
			throw new ResourceNotFoundException();
		item.setCatalog(catalog.get());
		itemService.updateItem(item);
		model.addAttribute("items", itemService.getAllItemsByCatalog(item.getCatalog(), page, pageSize));
		return ("redirect:/{cataId}/items");

	}

	@GetMapping("{cataId}/items/delete/{id}")
	public String getDeleteItemForm(@PathVariable Long cataId,@PathVariable Long id, Model model) {
		Optional<Catalog> catalog = catalogService.getCatalogById(cataId);
		if (!catalog.isPresent())
			throw new ResourceNotFoundException();
		Optional<Item>item = itemService.getItemByItemIdAndCatalog(id, catalog.get());
		if (!item.isPresent())
			throw new ResourceNotFoundException();
		model.addAttribute("item", item.get());
		return ("item-delete");

	}

	@PostMapping("{cataId}/items/delete/{id}")
	public String deleteItem(@PathVariable Long cataId, @RequestParam("page") Integer page,@PathVariable Long id, Model model) {
		if (!itemService.getItemById(id).isPresent())
			throw new ResourceNotFoundException();
		Optional<Catalog> catalog = catalogService.getCatalogById(cataId);
		if (!catalog.isPresent())
			throw new ResourceNotFoundException();
		itemService.deleteItem(id);
		model.addAttribute("items", itemService.getAllItemsByCatalog(catalog.get(), page, pageSize));
		return ("redirect:/{cataId}/items");

	}

}
