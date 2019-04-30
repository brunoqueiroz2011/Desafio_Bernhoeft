/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.DaoArchive;

/**
 *
 * @author Bruno
 */
public class ControllerAdditionalData {

    private DaoArchive archive;
    
    public ControllerAdditionalData() {
        archive = new DaoArchive();
    }        
    
    public void validFileImport(String filePath){
        if (filePath.equals(null) || filePath.equals("")) {
             //return ""         
        }else{
            archive.readFirtLineFile(filePath);
        }
    }
    
    public void validAdditionalData(){}        
    
    public void addDataArrayStudents(){}    
    
    
}
