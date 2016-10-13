/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author geotor
 */
@SpringBootApplication
@EnableAutoConfiguration
public class FileManagerApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(FileManagerApplication.class, args);
	}
}
