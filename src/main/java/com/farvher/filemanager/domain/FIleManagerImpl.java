/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.domain;

import java.io.File;
import java.io.FileFilter;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author farvher
 */
@Component
public class FIleManagerImpl implements FIleManager {

    public static final int INDEXDIR = 0;
    File file;
    Log log = LogFactory.getLog(FIleManagerImpl.class);
    @Override
    public File getFile(String ruta) {
        file = new File(ruta);
        return file;
    }

    @Override
    public File[] getRoot() {
        File[] roots = File.listRoots();//lista todos los directorios principales / - c:// - d:// 
        return roots;
    }

    @Override
    public File[] getRootFolder() {
        File[] files = this.getRoot();
        File[] folders = files[INDEXDIR].listFiles();
        return folders;

    }

    @Override
    public int getCantRoot() {
        int cantidadDeRoots = this.getRoot().length;
        return cantidadDeRoots;

    }

    @Override
    public File[] getFolder(String ruta) {
        log.info("archivos en la ruta"+ ruta);
        file = new File(ruta);
        if ( !file.isDirectory()){
        return file.getParentFile().listFiles();
        }
        
        return file.listFiles();
        
    }

    @Override
    public File getParent(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File searchFile(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File searchFile(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File searchFile(String name, String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File searchFile(String name, String type, File parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File searchFileByType(String Type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File searchFileByLength(String length) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
