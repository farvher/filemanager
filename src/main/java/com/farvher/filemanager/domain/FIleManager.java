/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farvher.filemanager.domain;

import java.io.File;

/**
 *
 * @author farvher
 */
public interface FIleManager {
    
    
    public File getFile(String ruta);
    
    
    public File[] getRoot();
    
    
    public File[] getRootFolder();
    
    
    public int getCantRoot();
    
    
    public File[]  getFolder(String ruta);
    
    
    public File  getParent(File file);
    
    

     
    
}
