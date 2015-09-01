/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author geotor
 */
public class FileSort {

    public static File[] orderByNameAsc(File[] archivos) {
        Arrays.sort(archivos, new Comparator<File>() {
            public int compare(File a, File b) {
                return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
            }
        });
        return archivos;

    }

    public static File[] orderByNameDes(File[] archivos) {
        Arrays.sort(archivos, new Comparator<File>() {
            public int compare(File a, File b) {
                return b.getName().toLowerCase().compareTo(a.getName().toLowerCase());
            }
        });
        return archivos;

    }

    public static File[] orderByTypeAsc(File[] archivos) {
       
        Arrays.sort(archivos, new Comparator<File>() {
            public int compare(File a, File b) {
                int com = 0;
                try {
                    String typeA = Files.probeContentType(Paths.get(a.getAbsolutePath()));
                    String typeB = Files.probeContentType(Paths.get(b.getAbsolutePath()));
                    com = typeB.compareTo(typeA);
                } catch (IOException ex) {
                }

                return com;
            }
        });
        return archivos;
    }

}
