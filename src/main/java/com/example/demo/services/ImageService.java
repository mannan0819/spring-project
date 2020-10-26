package com.example.demo.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageService implements IImageService{

	public String save(MultipartFile file) {
		
		// TODO Auto-generated method stub
		Path currentPath = Paths.get(".");
		String absolutePath = currentPath.toAbsolutePath() + "/src/main/resources/static/img/";
		String fileType = file.getContentType();
		String randString = UUID.randomUUID().toString().substring(0, 8);
		String fileName = randString+ "-" +file.getOriginalFilename();
		
		
		
		System.out.println(randString);
		if(!fileType.contains("image")) return "0";
		try {
			Files.write(Paths.get(absolutePath + fileName), file.getBytes());
		} catch(Exception e) {
			e.fillInStackTrace();
			return "0";
		}
		return fileName;

	}

	public String getFolderPath() {
		Path currentPath = Paths.get(".");
		String absolutePath = currentPath.toAbsolutePath() + "/src/main/resources/static/img/";
		return absolutePath;
	}

	public MultipartFile getFile(String filename) {
		// TODO Auto-generated method stub
		Path currentPath = Paths.get(".");
		String absolutePath = currentPath.toAbsolutePath() + "/src/main/resources/static/img/";
		if(Files.exists(Paths.get(absolutePath+filename))) {
			try {
				byte[] readAllBytes = Files.readAllBytes(Paths.get(absolutePath+filename));
			} catch (Exception e) {
				System.out.println("ERROR");
			}
			
		}
		return null;
	}

}
