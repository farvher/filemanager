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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Value(value = "${my.secret}")
	String secret ;

	/**
	 * Controlador principal de FileManager
	 * 
	 * @author farvher
	 */
	@RequestMapping(value = { "/", "" })
	public String getIndex(Model model) {
		File homeFolder  = new File(CURRENT_DIRECTORY);
		File[] tempFolder = homeFolder.listFiles();
		model.addAttribute(ROOT, tempFolder);
		model.addAttribute(NAVEGADOR, htmlUtil.getButtonsRuta(homeFolder));
		model.addAttribute(UBICADO, homeFolder);
		model.addAttribute(CANTIDAD, tempFolder.length);
		
		return INDEX;
	}

	@RequestMapping(value = { "/buscar/", "/buscar" })
	public String getContentAjax(@RequestParam String ruta, Model model) {
		File tempFile = new File(ruta);
		File[] tempFilesFinded = filemanager.getFolder(ruta);
		if (tempFilesFinded != null) {
			model.addAttribute(ROOT, tempFilesFinded);
			model.addAttribute(CANTIDAD, tempFilesFinded.length);
		}
		model.addAttribute(NAVEGADOR, htmlUtil.getButtonsRuta(tempFile));
		model.addAttribute(UBICADO, tempFile.getPath());
		previewFile(tempFile,model);
		return FILEMANAGER_PATH;
		
	}

	@RequestMapping(value = { "/filtro/", "/filtro" })
	public String getContentAjaxFiltro(@RequestParam String palabra, @RequestParam String buscardesde,
			Model model) {
		FileBuscador searcher = new FileBuscador();
		filesFinded = searcher.buscarPorPalabra(new File(buscardesde), palabra);

		if (filesFinded != null) {
			model.addAttribute(ROOT, filesFinded);
			model.addAttribute(CANTIDAD, filesFinded.length);
			if (filesFinded.length==1){
				previewFile(filesFinded[0],model);
			}
			model.addAttribute(NAVEGADOR, filesFinded.length + " resultados encontrados para '" + palabra + "'");
		}
		model.addAttribute(UBICADO, buscardesde);

		return FILEMANAGER_PATH;

	}

	
	private void previewFile(File previewFile ,Model model ){
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

	@RequestMapping(value = { "/error/404", "error/404" })
	public String error404(String mensajeError,Model model) {
		model.addAttribute(ERROR, mensajeError);
		return ERROR404_PATH;
	}

}
