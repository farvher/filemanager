/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.controller;

import com.farvher.filemanager.domain.FIleManager;
import com.farvher.filemanager.util.FileBuscador;
import com.farvher.filemanager.util.HtmlUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author geotor
 */
@Controller
public class MultipartController {

    @Autowired
    HtmlUtil htmlUtil;

    @Autowired
    FileBuscador buscador;

    @Autowired
    FIleManager filemanager;

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("ruta") String ruta,
            @RequestParam("file") MultipartFile file) throws IOException {
        try {
            String nameFile = file.getOriginalFilename();
            InputStream input = file.getInputStream();
            OutputStream ouput = new FileOutputStream(ruta + "/" + nameFile);
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

}
