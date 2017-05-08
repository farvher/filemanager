/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author geotor
 */
@SpringBootApplication
@EnableAutoConfiguration
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
