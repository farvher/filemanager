/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.util;

import java.io.File;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 *
 * @author farvher
 */
@Component
public class HtmlUtil {

    public String[] getButtonsRuta(File  file) {

        String ruta = file.getAbsolutePath();
        ruta = ruta.substring(1,ruta.length());
        String[] rutaSplit = ruta.split(Pattern.quote(File.separator));
        
        return rutaSplit;

    }
}
