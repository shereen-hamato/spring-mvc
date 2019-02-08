package com.shereen.catalog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	
	public boolean storeFile(MultipartFile file)  {
		//Save the uploaded file to this folder
	    String UPLOADED_FOLDER = "path/to/storage";
	    
		byte[] bytes;
		try {
			bytes = file.getBytes();
			 Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		        Files.write(path, bytes);
		        return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
       
	}

}
