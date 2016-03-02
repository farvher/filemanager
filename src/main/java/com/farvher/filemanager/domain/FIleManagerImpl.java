/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.domain;

import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
        return File.listRoots();
    }

    @Override
    public File[] getRootFolder() {
        File[] files = this.getRoot();
        return files[INDEXDIR].listFiles();

    }

    @Override
    public int getCantRoot() {
        return this.getRoot().length;

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

}
