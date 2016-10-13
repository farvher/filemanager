/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private static final int MAX_DEEP = 5;
    
    public File[] encontrado;
    
    public File[] buscarPorPalabra8(File dir, final String palabra) {
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
                logger.error("buscando '" + palabra + "' en " + dir.getName() + "-- found : " + encontrado.length);
                
                String[] childrenstring = dir.list();
                for (int i = 0; i < childrenstring.length; i++) {
                    dir = new File(dir, childrenstring[i]);
                    encontrado = buscarPorPalabra8(dir, palabra);
                    if (encontrado.length >= MAXIMO_RESULTADOS) {
                        break;
                    }
                }
            } catch (Exception e) {
                logger.error("error buscando " + e);
            }
        }
        return encontrado;
    }
    
    public File[] buscarPorPalabra(File dir, final String palabra) {
        try {
            Stream<File> files = Files.find(Paths.get(dir.getPath()), 
                    MAX_DEEP, 
                    (pathDir, fileAttrib) -> pathDir.toString().contains(palabra))
                    .map((path) -> path.toFile());
            return files.toArray(size -> new File[size]);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FileBuscador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
