/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.controller;

import com.farvher.filemanager.constants.MappingConstants;
import com.farvher.filemanager.constants.MessagesConstants;
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


	@Autowired
	HtmlUtil htmlUtil;

	@Autowired
	FileBuscador buscador;

	@Autowired
	FIleManager filemanager;

	@Autowired
	IndexController indexController;

	@Autowired
	MessagesConstants messagesConstants;

	@PostMapping(MappingConstants.FORM_PATH)
	public String handleFormUpload(@RequestParam String ruta, @RequestParam MultipartFile file,
			RedirectAttributes redirectAttributes) {
		String separador = File.separator;
		String nameFile = file.getOriginalFilename();
		try {
			Files.copy(file.getInputStream(), Paths.get(ruta).resolve(file.getOriginalFilename()));
//			logger.error(ruta + separador + nameFile);
			redirectAttributes.addFlashAttribute(SUCCESS,
					String.format(messagesConstants.archivoCargadoCorrecto, nameFile));
		} catch (Exception e) {
//			logger.error(messagesConstants.archivoCargadoError, e);
			redirectAttributes.addFlashAttribute(DANGER, messagesConstants.archivoCargadoError);
		}
		return REDIRECT_RUTA + ruta;
	}

	@GetMapping(MappingConstants.DOWNLOAD_PATH)
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
//			logger.error("error descargando archivo", ex);
		}
		return null;
	}

	@GetMapping(MappingConstants.CREATEFOLDER_PATH)
	public String createFolder(@RequestParam String ruta, Model model, RedirectAttributes redirectAttributes) {
		Path confDir = Paths.get(ruta);
		try {
			if (Files.notExists(confDir)) {
				Files.createDirectory(Paths.get(ruta));
				redirectAttributes.addFlashAttribute(SUCCESS,
						String.format(messagesConstants.directorioCreadoCorrecto, confDir.getFileName()));
			} else {
				redirectAttributes.addFlashAttribute(WARNING,
						String.format(messagesConstants.directorioCreadoExiste, confDir.getFileName()));
			}
		} catch (Exception ex) {
//			logger.error(messagesConstants.directorioCreadoError, ex);
			redirectAttributes.addFlashAttribute(DANGER, messagesConstants.directorioCreadoError);
		}
		return REDIRECT_RUTA + ruta;
	}

	@GetMapping(MappingConstants.DELETE)
	public String removeFile(@RequestParam String ruta, Model model, RedirectAttributes redirectAttributes) {
		File archivo = new File(ruta);
		String parent = archivo.getParent();
		if (archivo.delete()) {
			redirectAttributes.addFlashAttribute(SUCCESS,
					String.format(messagesConstants.archivoEliminadoCorrecto, archivo.getName()));
		} else {
			redirectAttributes.addFlashAttribute(WARNING, messagesConstants.archivoEliminadoError);
		}
		return REDIRECT_RUTA + parent;

	}

}
