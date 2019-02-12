package com.shereen.catalog.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	
	//Save the uploaded file to this folder
	private static String storagePath = System.getenv("catalog-storage-path");
	
	public boolean storeFile(MultipartFile file)  {
	    
		byte[] bytes;
		try {
			bytes = file.getBytes();
			 Path path = Paths.get(storagePath + file.getOriginalFilename());
		        Files.write(path, bytes);
		        return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
       
	}
	
	public Resource serveFile(String filename) throws MalformedURLException {
		Path storage = Paths.get(storagePath)
				.resolve(filename);
		return new UrlResource(storage.toUri());
	}

}
