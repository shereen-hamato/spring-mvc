package com.shereen.catalog.controller;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	private final Integer pageSize = 12;

	@GetMapping()
	public String getCatalog(Model model, @RequestParam("page") Integer page) {
		model.addAttribute("catalogs", cataService.getAllCatalogs(page, pageSize).getContent());
		model.addAttribute("pages", cataService.getAllCatalogs(page, pageSize));
		return "catalog-list";
	}

	@GetMapping("/JSON/{page}")
	@ResponseBody
	public Page<Catalog> getAllCatalogJson(@PathVariable Integer page) {
		return cataService.getAllCatalogs(page, pageSize);
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
	public String getAddCatalogForm(Catalog catalog) {
		return "catalog-new";
	}

	@PostMapping("/new")
	public String addCatalog(@RequestParam("file") MultipartFile file, @ModelAttribute @Valid Catalog catalog,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException {
System.out.println("file name "+file.getOriginalFilename());
		if (bindingResult.hasErrors()) {
			return "catalog-new";
		}

		if (file.getOriginalFilename()!=""){
			System.out.println("file name  "+file.getOriginalFilename());
		storageService.storeFile(file);
		catalog.setImagePath("http://localhost:8080/files/" + file.getOriginalFilename());
		}
		System.out.println(catalog.getImagePath());
		cataService.addCatalog(catalog);
		redirectAttributes.addAttribute("page", 0);
		model.addAttribute("catalogs", cataService.getAllCatalogs(0, pageSize));
		return ("redirect:/catalogs");
	}

	@GetMapping("/edit/{id}")
	public String getEditCatalogForm(Model model, @RequestParam Integer page, @PathVariable Long id, Catalog catalog) {
		Optional<Catalog> cata = cataService.getCatalogById(id);
		if (!cata.isPresent())
			throw new ResourceNotFoundException();
		model.addAttribute("catalog", cataService.getCatalogById(id).get());
		model.addAttribute("page", page);
		return "catalog-edit";
	}

	@PostMapping("/edit")
	public String editCatalog(@RequestParam("file") MultipartFile file, @ModelAttribute @Valid Catalog catalog,
			BindingResult bindingResult, @RequestParam Integer page, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("page", page);
			return "catalog-edit";
		}
		
		if (!file.getOriginalFilename().isEmpty()) {
			storageService.storeFile(file);
			catalog.setImagePath("http://localhost:8080/files/" + file.getOriginalFilename());
		}
		cataService.updateCatalg(catalog);
		model.addAttribute("catalogs", cataService.getAllCatalogs(page, pageSize));
		redirectAttributes.addAttribute("page", page);
		return ("redirect:/catalogs");

	}

	@GetMapping("/delete/{id}")
	public String getdeleteCatalogForm(Model model, @RequestParam Integer page, @PathVariable Long id) {
		Optional<Catalog> cata = cataService.getCatalogById(id);
		if (!cata.isPresent())
			throw new ResourceNotFoundException();
		model.addAttribute("catalog", cataService.getCatalogById(id).get());
		model.addAttribute("page", page);
		return "catalog-delete";
	}

	@PostMapping("/delete/{id}")
	public String deleteCatalog(@RequestParam Integer page, @PathVariable Long id, Model model,
			RedirectAttributes redirectAttributes) {
		Optional<Catalog> cata = cataService.getCatalogById(id);
		if (!cata.isPresent())
			throw new ResourceNotFoundException();
		cataService.deleteCatalog(id);
		model.addAttribute("catalogs", cataService.getAllCatalogs(page, pageSize));
		redirectAttributes.addAttribute("page", page);
		return ("redirect:/catalogs");

	}

}
