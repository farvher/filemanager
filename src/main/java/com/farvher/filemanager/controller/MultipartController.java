/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.controller;

import com.farvher.filemanager.domain.FIleManager;
import com.farvher.filemanager.util.FileBuscador;
import com.farvher.filemanager.util.HtmlUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author geotor
 */
@Controller
public class MultipartController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(MultipartController.class);

	@Autowired
	HtmlUtil htmlUtil;

	@Autowired
	FileBuscador buscador;

	@Autowired
	FIleManager filemanager;

	@Autowired
	IndexController indexController;

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("ruta") String ruta, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		String separador = File.separator;
		String nameFile = file.getOriginalFilename();
		logger.error("Archivo cargado en " + ruta + separador + nameFile);
		try {
			Files.copy(file.getInputStream(), Paths.get(ruta).resolve(file.getOriginalFilename()));
			redirectAttributes.addFlashAttribute("success", file.getOriginalFilename() + " cargado correctamente");
		} catch (Exception e) {
			logger.error("ha ocurrido un error cargando el archivo" + e);
			redirectAttributes.addFlashAttribute("danger", "No se pudo cargar el archivo");
		}
		return "redirect:/";
	}

	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@RequestParam("file_name") String filename) {
		try {
			Path file = Paths.get(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {

				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + resource.getFilename() + "\"").body(resource);

			}
		} catch (Exception ex) {
			logger.error("error cargando archivo", ex);
		}
		return null;
	}

	@PostMapping("/createFolder")
	public String createFolder(@RequestParam String folder_name, Model model, RedirectAttributes redirectAttributes) {
		Path confDir = Paths.get(folder_name);
		try {
			if (Files.notExists(confDir)) {
				Files.createDirectory(Paths.get(folder_name));
				redirectAttributes.addFlashAttribute("success", "Directorio creado");
			} else {
				redirectAttributes.addFlashAttribute("warning", "Directorio ya existe");
			}
		} catch (Exception ex) {
			logger.error("error creando directorio", ex);
			redirectAttributes.addFlashAttribute("danger", "No se pudo crear el directorio");
		}
		return indexController.getContentAjax(folder_name, model);
	}

	@PostMapping(value = "/delete")
	public String removeFile(@RequestParam("file_name") String fileName,Model model) {
		File archivo = new File(fileName);
		String parent = archivo.getParent();
		String msj = archivo.delete() ? "Borrado Correctamente" : "No se pudo Borrar";
		return indexController.getContentAjax(parent, model);
		
	}

}
