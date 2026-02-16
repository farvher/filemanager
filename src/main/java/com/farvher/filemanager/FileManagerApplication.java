package com.farvher.filemanager;

import java.io.File;

import jakarta.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author geotor
 */
@SpringBootApplication
public class FileManagerApplication {

	private final static String FOLDERAPP = "/files-manager/";
	
	public static void main(String[] args) {
		SpringApplication.run(FileManagerApplication.class, args);
	}

	@PostConstruct
	public void generateRootFolderApp() {
		String SystemHomer = System.getProperty("user.home");

		File folderApp = new File(SystemHomer.concat(FOLDERAPP));
		
		if(!folderApp.exists()){
			folderApp.mkdir();
		}
	}

}
