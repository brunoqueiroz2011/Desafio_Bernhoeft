/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.DaoArchive;
import Dao.DaoStudent;
import Singleton.ListStudents;
import Useful.indicators;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author Bruno
 */
public class ControllerAdditionalData {

    private DaoArchive archive;
    String registration, teamName, email;
    int user, teamId, month;
    Boolean delayedActions, trainingCompliance;
    
    
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
    
    public void validAdditionalData(String registration, String user, String teamId, String teamName, String email, int month, Boolean delayedActions, Boolean trainingCompliance){
        if(!registration.isEmpty())
            this.registration = registration;
        if(!user.isEmpty())
            this.user = Integer.parseInt(user);
        if(!teamId.isEmpty())
            this.teamId = Integer.parseInt(teamId);
        if(!teamName.isEmpty())
            this.teamName = teamName;
        if(!email.isEmpty())
            this.email = email;        
            
        this.month = month;
        
        this.delayedActions = delayedActions;
        this.trainingCompliance = trainingCompliance;
    }     
    
    public void addDataArrayStudents(String filePath) throws IOException, ParseException{
        DaoStudent daoStudent;
        daoStudent = new DaoStudent(archive.readAndTreatFile(filePath, this.month));        
        daoStudent.totalActivities(this.email);
        if (delayedActions) {            
            daoStudent.addNewStudent(this.registration, this.user, this.month,this.teamId, this.teamName,indicators.INDICADOR_ID_ACOES_ATRASADAS, indicators.INDICADOR_ACOES_ATRASADAS,daoStudent.amountOverdueActivities());
            daoStudent.addStudentInList();
        }
        if (trainingCompliance) {            
            daoStudent.addNewStudent(this.registration, this.user, this.month,this.teamId, this.teamName,indicators.INDICADOR_ID_CUMPRIMENTO_TREINAMENTO_JEDI, indicators.INDICADOR_CUMPRIMENTO_TREINAMENTO_JEDI,daoStudent.quantityActivitiesDone());
            daoStudent.addStudentInList();
        }        
        System.out.println(daoStudent.getStudents());
    }    
    
    
}
