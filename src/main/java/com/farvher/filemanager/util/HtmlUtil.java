/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.regex.Pattern;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author farvher
 */
@Component
public class HtmlUtil {

    public String[] getButtonsRuta(File file) {

        String ruta = file.getAbsolutePath();
        ruta = ruta.substring(1, ruta.length());
        String[] rutaSplit = ruta.split(Pattern.quote(File.separator));

        return rutaSplit;

    }

    public String processImgHtml(String ruta) {
        String img = "";
        try {
            File imgFile = new File(ruta);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(imgFile));
            byte[] imageBytes = new byte[0];
            for (byte[] ba = new byte[bis.available()];
                    bis.read(ba) != -1;) {
                byte[] baTmp = new byte[imageBytes.length + ba.length];
                System.arraycopy(imageBytes, 0, baTmp, 0, imageBytes.length);
                System.arraycopy(ba, 0, baTmp, imageBytes.length, ba.length);
                imageBytes = baTmp;
            }
            img = "<img  class='img-responsive' src='data:image/png;base64," + DatatypeConverter.printBase64Binary(imageBytes) + "'>";

        } catch (Exception e) {
            System.out.println("No se pudo procesar la imagen " + e.getMessage());
        }

        return img;

    }

}
