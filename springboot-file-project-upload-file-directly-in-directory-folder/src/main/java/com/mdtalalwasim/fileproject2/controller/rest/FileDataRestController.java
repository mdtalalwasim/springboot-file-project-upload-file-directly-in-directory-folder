package com.mdtalalwasim.fileproject2.controller.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mdtalalwasim.fileproject2.service.FileDataService;

@RestController
@RequestMapping("/api")
public class FileDataRestController {
	

	
	@Autowired
	private FileDataService fileDataService;
	
	@PostMapping("/file-upload-to-directory")
	public ResponseEntity<?> uploadImageToFileDirectory(@RequestParam("file") MultipartFile file) throws IOException{
		String uploadFile = fileDataService.uploadFileToFileDirectory(file);
		
		return ResponseEntity.status(HttpStatus.OK).body(uploadFile);
		
		 
	}
	
	@GetMapping("/file-download-from-directory/{fileName}")
	public ResponseEntity<?> downloadImageFromFileDirectory(@PathVariable String fileName) throws IOException{
		byte[] downloadFile = fileDataService.downloadFileFromFileDirectory(fileName);
				
		
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(downloadFile);
	}

}
