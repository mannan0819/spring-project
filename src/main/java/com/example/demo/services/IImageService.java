package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IImageService {
	public String save(MultipartFile File);
	public MultipartFile getFile(String filename);
	public String getFolderPath();
	
}
