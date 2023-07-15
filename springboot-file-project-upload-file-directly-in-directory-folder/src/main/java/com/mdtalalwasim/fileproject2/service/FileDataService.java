package com.mdtalalwasim.fileproject2.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileDataService {

	String uploadFileToFileDirectory(MultipartFile file) throws IOException;

	byte[] downloadFileFromFileDirectory(String fileName) throws IOException;

}
