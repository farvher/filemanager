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
import java.nio.file.Files;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author geotor
 */
@Controller
public class MultipartController {

    private static final int BUFFER_SIZE = 4096;

    @Autowired
    HtmlUtil htmlUtil;

    @Autowired
    FileBuscador buscador;

    @Autowired
    FIleManager filemanager;

    @Autowired
    indexController indexController;

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("ruta") String ruta,
            @RequestParam("file") MultipartFile file, ModelAndView model) throws IOException {
        try {

            String separador = File.separator;
            String nameFile = file.getOriginalFilename();
            System.out.println("Archivo cargado en " + ruta + separador + nameFile);
            InputStream input = file.getInputStream();
            OutputStream ouput = new FileOutputStream(ruta + separador + nameFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = input.read(bytes)) != -1) {
                ouput.write(bytes, 0, read);
            }

        } catch (Exception e) {
            System.out.println("ha ocurrido un error cargando el archivo" + e.getMessage());
        } finally {
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void getFile(@RequestParam("file_name") String fileName,
            HttpServletResponse response) {
        try {
            // get your file as InputStream
            File archivo = new File(fileName);
            InputStream is = new FileInputStream(archivo);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + archivo.getName() + "\"");
            response.flushBuffer();
        } catch (Exception ex) {
            System.out.println("error descargando" + ex.getMessage());
        }

    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void removeFile(@RequestParam("file_name") String fileName,
            HttpServletResponse response) {
        try {
            // get your file as InputStream
            File archivo = new File(fileName);
            archivo.delete();
            InputStream is = new FileInputStream(archivo);
            // copy it to response's OutputStream
        } catch (Exception ex) {
            System.out.println("error borrando" + ex.getMessage());
        }

    }

}
