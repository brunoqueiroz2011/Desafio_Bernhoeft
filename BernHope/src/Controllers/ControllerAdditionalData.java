/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.DaoArchive;
import java.io.IOException;

/**
 *
 * @author Bruno
 */
public class ControllerAdditionalData {

    private DaoArchive archive;
    
    public ControllerAdditionalData() {
        archive = new DaoArchive();
    }        
    
    public String validFileImport(String filePath) throws IOException{
        String result = null;
        if (filePath.equals(null) || filePath.equals("") || filePath.equals("Nenhum arquivo selecionado")) {
             result = "Erros de Caminho";
        }else{
            result = archive.readFirtLineFile(filePath);
        }
        return result;
    }
    
    public void validAdditionalData(String registration, String user, String teamId, Boolean delayedActions, Boolean trainingCompliance){
        
    }        
    
    public void addDataArrayStudents(){}    
    
    
}
