/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.controller;

import com.farvher.filemanager.constants.MappingConstants;
import com.farvher.filemanager.constants.MessagesConstants;
import com.farvher.filemanager.domain.FIleManager;
import com.farvher.filemanager.services.SecurityService;
import com.farvher.filemanager.util.FileBuscador;
import com.farvher.filemanager.util.FileSort;
import com.farvher.filemanager.util.HtmlUtil;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author farvher
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	HtmlUtil htmlUtil;

	@Autowired
	FileBuscador buscador;

	@Autowired
	FIleManager filemanager;

	@Autowired
	SecurityService secureService;

	@Autowired
	MessagesConstants messagesConstants;

	File[] filesFinded;

	@Value("${resultados.cantidad.encontrados}")
	private String cantidad;

	/**
	 * Controlador principal de FileManager
	 * 
	 * @author farvher
	 */
	@GetMapping(MappingConstants.INDEX_PATH)
	public String getIndex(Model model, String ruta) {
		File homeFolder = this.getFolderFromPath(ruta);
		File[] tempFolder = homeFolder.listFiles();
		model.addAttribute(ROOT, tempFolder);
		model.addAttribute(NAVEGADOR, htmlUtil.getButtonsRuta(homeFolder));
		model.addAttribute(UBICADO, homeFolder);
		model.addAttribute(CANTIDAD, tempFolder.length);
		return INDEX;
	}

	@PostMapping(MappingConstants.SEARCH_PATH)
	public String getContentAjax(@RequestParam String ruta, Model model) {
		File tempFile = new File(ruta);
		File[] tempFilesFinded = filemanager.getFolder(ruta);
		if (tempFilesFinded != null) {
			model.addAttribute(ROOT, tempFilesFinded);
			model.addAttribute(CANTIDAD, tempFilesFinded.length);
		}
		model.addAttribute(NAVEGADOR, htmlUtil.getButtonsRuta(tempFile));
		model.addAttribute(UBICADO, tempFile.getPath());
		previewFile(tempFile, model);
		return FILEMANAGER_PATH;
	}

	@PostMapping(MappingConstants.FILTER_PATH)
	public String getContentAjaxFiltro(@RequestParam String palabra, @RequestParam String buscardesde, Model model) {
		FileBuscador searcher = new FileBuscador();
		filesFinded = searcher.buscarPorPalabra(new File(buscardesde), palabra);

		if (filesFinded != null) {
			model.addAttribute(ROOT, filesFinded);
			model.addAttribute(CANTIDAD, filesFinded.length);
			if (filesFinded.length == 1) {
				previewFile(filesFinded[0], model);
			}
			model.addAttribute(NAVEGADOR,
					String.format(messagesConstants.resultadosEncontrados, palabra, filesFinded.length));
		}
		model.addAttribute(UBICADO, buscardesde);

		return FILEMANAGER_PATH;

	}

	@GetMapping(MappingConstants.ERROR404_PATH)
	public String error404(String mensajeError, Model model) {
		model.addAttribute(ERROR, mensajeError);
		return ERROR404_PATH;
	}

	private File getFolderFromPath(String ruta) {
		if (ruta != null) {
			Path path = Paths.get(ruta);
			if (Files.exists(path) && Files.isDirectory(path)) {
				return path.toFile();
			} else if (Files.exists(path)) {
				return path.toFile().getParentFile();
			}
		}
		return new File(CURRENT_DIRECTORY);
	}

	private void previewFile(File previewFile, Model model) {
		if (!previewFile.isDirectory()) {
			String tiPoArchivo = FileSort.getFileType(previewFile);
			if (tiPoArchivo.contains(IMAGE)) {
				model.addAttribute(IMG, htmlUtil.processImgHtml(previewFile.getPath()));
			} else {
				model.addAttribute(IMG, FileSort.readFileAsString(previewFile.getAbsolutePath()));
			}
			model.addAttribute(TIPO, tiPoArchivo);
			model.addAttribute(UBICADO, previewFile.getParent());
		}
	}

}
