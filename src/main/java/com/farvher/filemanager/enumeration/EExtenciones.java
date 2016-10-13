/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.enumeration;

/**
 *
 * @author geotor
 */
public enum EExtenciones {

    TXT("texto", ".txt"),
    IMG("imagen", ".img"),
    PNG("png",".png"),
    JAVA("java",".java"),
    XML("xml",".xml");

    public String name;
    public String ext;

    EExtenciones(String name, String ext) {
        this.ext = ext;
        this.name = name;
    }

}
