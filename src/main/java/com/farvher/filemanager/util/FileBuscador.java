/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.util;

import java.io.File;
import java.io.FilenameFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author farvher
 */
@Component
public class FileBuscador {

    private static final Logger logger = LoggerFactory.getLogger(FileBuscador.class);
	
    private static final int MAXIMO_RESULTADOS = 3000;
    private static final int MAXIMO_TOKENS = 10000;

    public File[] encontrado;

    public File[] buscarPorPalabra(File dir, final String palabra) {
        if (dir.isDirectory() && !dir.isHidden()) {
            try {
                FilenameFilter filter = new FilenameFilter() {
                	@Override
                    public boolean accept(File dir, String name) {
                        return name.contains(palabra);
                    }
                };
                File[] children = dir.listFiles(filter);

                encontrado = (File[]) ArrayUtils.addAll(children, encontrado);
                logger.error("buscando '"+palabra+"' en " + dir.getName()+ "-- found : " + encontrado.length );

                String[] childrenstring = dir.list();
                for (int i = 0; i < childrenstring.length; i++) {
                    File temfile = new File(dir, childrenstring[i]);
                    encontrado = buscarPorPalabra(temfile, palabra);
                    if (encontrado.length >= MAXIMO_RESULTADOS) {
                        break;
                    }
                }
            } catch (Exception e) {
                logger.error("error buscando "+e);
            }
        }
        return encontrado;
    }

    public static void visitAllDirsAndFiles(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                visitAllDirsAndFiles(new File(dir, children[i]));
            }
        }
    }

}
