/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author farvher
 */
@Component
public class FileBuscador {

    private final static int MAXIMO_RESULTADOS = 1000;
    private final static int MAXIMO_TOKENS = 10000;
    private static int token = 0;

    public File[] encontrado;

    public File[] buscarPorPalabra(File dir, final String palabra) {
        if (dir.isDirectory() && !dir.isHidden()) {
            token++;
            try {
                FilenameFilter filter = new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.contains(palabra);
                    }
                };
                File[] children = dir.listFiles(filter);

                encontrado = (File[]) ArrayUtils.addAll(children, encontrado);
                System.out.println("buscando '"+palabra+"' en " + dir.getName()+ "-- found : " + encontrado.length +" -- tok :"+token);

                String[] childrenstring = dir.list();
                for (int i = 0; i < childrenstring.length; i++) {
                    File temfile = new File(dir, childrenstring[i]);
                    encontrado = buscarPorPalabra(temfile, palabra);
                    if (token >= MAXIMO_TOKENS) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.getMessage();
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
