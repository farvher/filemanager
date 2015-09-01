/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.controller;

import com.farvher.filemanager.domain.FIleManager;
import com.farvher.filemanager.util.FileBuscador;
import com.farvher.filemanager.util.FileSort;
import com.farvher.filemanager.util.HtmlUtil;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
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
public class indexController {

    @Autowired
    HtmlUtil htmlUtil;

    @Autowired
    FileBuscador buscador;

    @Autowired
    FIleManager filemanager;

    @RequestMapping(value = {"/", ""})
    public ModelAndView getIndex(ModelAndView model) {
        String curDir = System.getProperty("user.home");
        File[] tempFolder = new File(curDir).listFiles();
        tempFolder= FileSort.orderByTypeAsc(tempFolder);

        model.addObject("root", tempFolder);
        model.addObject("navegador", htmlUtil.getButtonsRuta(tempFolder[0].getParentFile()));
        model.addObject("ubicado", tempFolder[0].getParent());
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = {"/buscar/", "/buscar"})
    public ModelAndView getContentAjax(@RequestParam String ruta, ModelAndView model) {
        File tempFile = new File(ruta);
        File[] filesFinded = filemanager.getFolder(ruta);
        if (filesFinded != null) {
            filesFinded = FileSort.orderByTypeAsc(filesFinded);
            model.addObject("root", filesFinded);
        }
        model.addObject("navegador", htmlUtil.getButtonsRuta(tempFile));
        model.addObject("ubicado", tempFile.getPath());

        if (!tempFile.isDirectory()) {
            String tiPoArchivo = FileSort.getFileType(tempFile);
            System.out.println("archivo tipo "+tiPoArchivo);
            model.addObject("img", htmlUtil.processImgHtml(tempFile.getPath()));
            model.addObject("tipo",tiPoArchivo);
        }

        model.setViewName("content/filemanager");
        return model;
    }

    @RequestMapping(value = {"/filtro/", "/filtro"})
    public ModelAndView getContentAjaxFiltro(@RequestParam String palabra, ModelAndView model) {
        FileBuscador searcher = new FileBuscador();
        String buscarDesde = System.getProperty("user.home");
        File[] filesFinded = searcher.buscarPorPalabra(new File(buscarDesde), palabra);

        if (filesFinded != null) {
            filesFinded = FileSort.orderByTypeAsc(filesFinded);
            model.addObject("root", filesFinded);
        }
        model.addObject("navegador", filesFinded.length + " resultados encontrados para '" + palabra + "'");
        model.setViewName("content/filemanager");

        return model;
    }

    @RequestMapping(value = {"/error/404", "error/404"})
    public ModelAndView error404(String mensajeError) {
        ModelAndView model = new ModelAndView("error/404");
        model.addObject("error", mensajeError);
        return model;
    }

}
