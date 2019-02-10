package com.shereen.catalog.controller;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shereen.catalog.service.StorageService;

@Controller
@RequestMapping("/files")
public class StorageController {
	
	@Autowired
	StorageService storageService;

	@GetMapping("/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveImage(@PathVariable String filename) throws MalformedURLException {

		Resource file =  storageService.serveFile(filename);
		if (file.exists() || file.isReadable()) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
		}
		return null;

	}
}
