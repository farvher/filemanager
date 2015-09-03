/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

//    public static File[] orderByTypeDesc(File[] archivos) {
//
//        Arrays.sort(archivos, new Comparator<File>() {
//            public int compare(File a, File b) {
//                int com = 0;
//                try {
//                    String typeA = Files.probeContentType(Paths.get(a.getAbsolutePath()));
//                    String typeB = Files.probeContentType(Paths.get(b.getAbsolutePath()));
//                    com = typeA.compareTo(typeB);
//                } catch (IOException ex) {
//                }
//
//                return com;
//            }
//        });
//        return archivos;
//    }

    public static String getFileType(File f) {

        String type = "";
        try {
            type = Files.probeContentType(Paths.get(f.getAbsolutePath()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return type;

    }

    public static boolean isImagen(String tipo) {
        return tipo.contains("image");

    }

    public static String readFileAsString(String filePath) {
        StringBuffer fileData = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(filePath));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("No se pudo convertir el archivo");
        }
        String resultado = fileData.toString().length() >= 2000 ? fileData.toString().substring(0, 1900) + " ...  " : fileData.toString();
        String nombre = "<h2>" + new File(filePath).getName() + "</h2>";

        return nombre + resultado;
    }
    
    

}
