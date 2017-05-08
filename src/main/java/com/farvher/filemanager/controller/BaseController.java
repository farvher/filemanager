package com.farvher.filemanager.controller;


public class BaseController {
	
	public static final String CANTIDAD ="cantidad";
	public static final String NAVEGADOR ="navegador";  
	public static final String UBICADO ="ubicado";
	public static final String ROOT = "root";
	public static final String IMG ="img";
	public static final String IMAGE ="image";
	public static final String TIPO = "tipo" ;
	public static final String INDEX = "index";
	public static final String FILEMANAGER_PATH = "content/filemanager"; 
	public static final String ERROR = "error";
	public static final String ERROR404_PATH = "error/404";
	private final static String FOLDERAPP = "/files-manager/";
	public static final String CURRENT_DIRECTORY = System.getProperty("user.home")+ FOLDERAPP;
	
	public BaseController() {
		
		
	}
}
