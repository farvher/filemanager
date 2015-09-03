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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

    /*MAPEO INICIO
     @autor Farith Sanmiguel
     en la pagina de inicio de la aplicacion lista
     todos los archivos encontrados en la carpeta del usuario 
     logueado en el SO
     EJ: /home/farith - c://users/farith
     */
    @RequestMapping(value = {"/", ""})
    public ModelAndView getIndex(ModelAndView model) {
        String curDir = System.getProperty("user.home");
        File[] tempFolder = new File(curDir).listFiles();
        tempFolder = FileSort.orderByTypeAsc(tempFolder);

        model.addObject("root", tempFolder);
        model.addObject("navegador", htmlUtil.getButtonsRuta(tempFolder[0].getParentFile()));
        model.addObject("ubicado", tempFolder[0].getParent());
        model.setViewName("index");
        return model;
    }

    /*MAPEO NAVEGADOR
     @autor Farith Sanmiguel
     recibe como parametro la ruta a donde desea ir , y lista todos
     los archivos contenidos dentro del directorio
     Si NO es un directorio, muestra la ruta padre y el contenido
     del archivo
  
     */
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
            if (tiPoArchivo.contains("image")) {
                model.addObject("img", htmlUtil.processImgHtml(tempFile.getPath()));
            } else {
                model.addObject("img", FileSort.readFileAsString(tempFile.getAbsolutePath()));
            }
            model.addObject("tipo", tiPoArchivo);
        }

        model.setViewName("content/filemanager");
        return model;
    }

    /*MAPEO BUSCADOR DE ARCHIVOS
     @autor Farith sanmiguel
     El metodo recibe la palabra que desea buscar y la ruta
     donde realizara la busqueda 
     */
    @RequestMapping(value = {"/filtro/", "/filtro"})
    public ModelAndView getContentAjaxFiltro(@RequestParam String palabra, @RequestParam String buscardesde, ModelAndView model) {
        FileBuscador searcher = new FileBuscador();
        //   String buscardesde = System.getProperty("user.home");
        File[] filesFinded = searcher.buscarPorPalabra(new File(buscardesde), palabra);

        if (filesFinded != null) {
            filesFinded = FileSort.orderByTypeAsc(filesFinded);
            model.addObject("root", filesFinded);
        }
        model.addObject("ubicado", buscardesde);
        model.addObject("navegador", filesFinded.length + " resultados encontrados para '" + palabra + "'");
        model.setViewName("content/filemanager");

        return model;
    }

    /*MAPEO DE ERROR 404 
     @autor Farith sanmiguel
     En el web.xml se especifica que al no encontrar un recurso 
     debe redireccionar al siguiente controlador, este renderizara 
     una vista donde nos muestra un mensaje y un boton para 
     retornar al inicio
     */
    @RequestMapping(value = {"/error/404", "error/404"})
    public ModelAndView error404(String mensajeError) {
        ModelAndView model = new ModelAndView("error/404");
        model.addObject("error", mensajeError);
        return model;
    }

}
