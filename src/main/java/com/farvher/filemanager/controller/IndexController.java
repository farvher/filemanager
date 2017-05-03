/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.controller;

import com.farvher.filemanager.domain.FIleManager;
import com.farvher.filemanager.services.SecurityService;
import com.farvher.filemanager.util.FileBuscador;
import com.farvher.filemanager.util.FileSort;
import com.farvher.filemanager.util.HtmlUtil;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	File[] filesFinded;

	/**
	 * Controlador principal de FileManager
	 * 
	 * @author farvher
	 */
	@RequestMapping(value = { "/", "" })
	public ModelAndView getIndex(ModelAndView model) {
		String currentUser = secureService.findLoggedInUsername();
		File[] tempFolder = new File(CURRENT_DIRECTORY).listFiles();
		model.addObject(ROOT, tempFolder);
		model.addObject(NAVEGADOR, htmlUtil.getButtonsRuta(tempFolder[0].getParentFile()));
		model.addObject(UBICADO, tempFolder[0].getParent());
		model.addObject(CANTIDAD, tempFolder.length);
		model.setViewName(INDEX);
		return model;
	}

	@RequestMapping(value = { "/buscar/", "/buscar" })
	public ModelAndView getContentAjax(@RequestParam String ruta, ModelAndView model) {
		File tempFile = new File(ruta);
		File[] tempFilesFinded = filemanager.getFolder(ruta);
		if (tempFilesFinded != null) {
			model.addObject(ROOT, tempFilesFinded);
			model.addObject(CANTIDAD, tempFilesFinded.length);
		}
		model.addObject(NAVEGADOR, htmlUtil.getButtonsRuta(tempFile));
		model.addObject(UBICADO, tempFile.getPath());

		/* FILE ES UN ARCHIVO Y NO UN DIRECTORIO */
		if (!tempFile.isDirectory()) {
			String tiPoArchivo = FileSort.getFileType(tempFile);
			if (tiPoArchivo.contains(IMAGE)) {
				model.addObject(IMG, htmlUtil.processImgHtml(tempFile.getPath()));
			} else {
				model.addObject(IMG, FileSort.readFileAsString(tempFile.getAbsolutePath()));
			}
			model.addObject(TIPO, tiPoArchivo);
			model.addObject(UBICADO, tempFile.getParent());
		}
		model.setViewName(FILEMANAGER_PATH);
		return model;
	}

	@RequestMapping(value = { "/filtro/", "/filtro" })
	public ModelAndView getContentAjaxFiltro(@RequestParam String palabra, @RequestParam String buscardesde,
			ModelAndView model) {
		FileBuscador searcher = new FileBuscador();
		filesFinded = searcher.buscarPorPalabra(new File(buscardesde), palabra);

		if (filesFinded != null) {
			model.addObject(ROOT, filesFinded);
			model.addObject(CANTIDAD, filesFinded.length);
			model.addObject(NAVEGADOR, filesFinded.length + " resultados encontrados para '" + palabra + "'");
		}
		model.addObject(UBICADO, buscardesde);

		model.setViewName(FILEMANAGER_PATH);

		return model;
	}

	@RequestMapping(value = { "/error/404", "error/404" })
	public ModelAndView error404(String mensajeError) {
		ModelAndView model = new ModelAndView(ERROR404_PATH);
		model.addObject(ERROR, mensajeError);
		return model;
	}

}
