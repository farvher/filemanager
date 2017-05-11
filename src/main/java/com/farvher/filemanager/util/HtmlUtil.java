/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author farvher
 */
@Component
public class HtmlUtil {
	
	private final Logger logger = LoggerFactory.getLogger(HtmlUtil.class);
	

    public String[] getButtonsRuta(File file) {
        String ruta = file.getAbsolutePath();
        if(!file.isDirectory()){
        	ruta = file.getParent();
        }
        ruta = ruta.substring(1, ruta.length());
        return  ruta.split(Pattern.quote(File.separator));
    }

    public String processImgHtml(String ruta) {
        String img = "";
        File imgFile = new File(ruta);
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(imgFile))) {
            byte[] imageBytes = new byte[0];
            for (byte[] ba = new byte[bis.available()];
                    bis.read(ba) != -1;) {
                byte[] baTmp = new byte[imageBytes.length + ba.length];
                System.arraycopy(imageBytes, 0, baTmp, 0, imageBytes.length);
                System.arraycopy(ba, 0, baTmp, imageBytes.length, ba.length);
                imageBytes = baTmp;
            }
            img = "<img  class='img-responsive img-rounded img-thumbnail' src='data:image/png;base64," + DatatypeConverter.printBase64Binary(imageBytes) + "'>";

        } catch (Exception e) {
            logger.error("No se pudo procesar la imagen " + e);
        }

        return img;

    }

}
