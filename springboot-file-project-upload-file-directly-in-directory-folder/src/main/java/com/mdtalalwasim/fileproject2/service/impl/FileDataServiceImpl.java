package com.mdtalalwasim.fileproject2.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mdtalalwasim.fileproject2.entity.FileData;
import com.mdtalalwasim.fileproject2.repository.FileDataRepository;
import com.mdtalalwasim.fileproject2.service.FileDataService;

@Service
public class FileDataServiceImpl implements FileDataService{
	
	@Autowired
	private FileDataRepository fileDataRepository;
	
	//private final String FILE_PATH = "/home/wasim/git/springboot-file-project-upload-file-directly-in-directory-folder/springboot-file-project-upload-file-directly-in-directory-folder/uploads/";
	private final String FILE_PATH = "/home/wasim/git/springboot-file-project-upload-file-directly-in-directory-folder/springboot-file-project-upload-file-directly-in-directory-folder/uploads/";

	@Override
	public String uploadFileToFileDirectory(MultipartFile file) throws IOException {
		String filePath = FILE_PATH+file.getOriginalFilename();//absolute path
		// TODO Auto-generated method stub
		FileData fileData = fileDataRepository.save(FileData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.filePath(filePath).build());
		
		//copy your file into that particular path
		file.transferTo(new java.io.File(filePath)); 
		
		if(fileData!= null) {
				return "file uploaded successfully : "+file.getOriginalFilename()+ " and Files uploaded path is :"+filePath;
		}
		return null;
	}

	@Override
	public byte[] downloadFileFromFileDirectory(String fileName) throws IOException {
		// TODO Auto-generated method stub
		
		
	Optional<FileData> fileDataObj = fileDataRepository.findByName(fileName);
	
	//first need to get the file path
	String filePath = fileDataObj.get().getFilePath();
	
	//got the file, now decompress it.
	byte[] imageFile = Files.readAllBytes(new java.io.File(filePath).toPath()); 
	
	return imageFile;
		
	}

}
