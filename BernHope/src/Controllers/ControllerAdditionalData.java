/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.DaoArchive;
import Dao.DaoStudent;
import Models.Student;
import Singleton.ListStudents;
import Useful.indicators;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno
 */
public class ControllerAdditionalData {

    private DaoArchive archive;
    private DaoStudent daoStudent;
    String registration, teamName, userAsana;
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
    
    public void validAdditionalData(String registration, String user, String teamId, String teamName, String userAsana, int month, Boolean delayedActions, Boolean trainingCompliance){
        if(!registration.isEmpty())
            this.registration = registration;
        if(!user.isEmpty())
            this.user = Integer.parseInt(user);
        if(!teamId.isEmpty())
            this.teamId = Integer.parseInt(teamId);
        if(!teamName.isEmpty())
            this.teamName = teamName;
        if(!userAsana.isEmpty())
            this.userAsana = userAsana;        
            
        this.month = month;
        
        this.delayedActions = delayedActions;
        this.trainingCompliance = trainingCompliance;
    }     
    
    public String userHaveActivity(){
        return "";
    }
    
    public int addDataArrayStudents(String filePath) throws IOException, ParseException{
        Student student = new Student(); 
        int userHaveActivity;
        
        if (archive.getListdadoscsv().isEmpty()) {
            daoStudent = new DaoStudent(archive.readAndTreatFile(filePath, this.month));   
        }        
        daoStudent.totalActivities(this.userAsana);                
        userHaveActivity = daoStudent.getActivityCount();
        if (userHaveActivity == 0) {            
            return userHaveActivity;
        }
        
        if (delayedActions) {            
            student = daoStudent.addNewStudent(this.registration, this.user, this.month,this.teamId, this.teamName,indicators.INDICADOR_ID_ACOES_ATRASADAS, indicators.INDICADOR_ACOES_ATRASADAS,daoStudent.amountOverdueActivities(this.userAsana));
            daoStudent.addStudentInList(student);
        }
        if (trainingCompliance) {            
            student = daoStudent.addNewStudent(this.registration, this.user, this.month,this.teamId, this.teamName,indicators.INDICADOR_ID_CUMPRIMENTO_TREINAMENTO_JEDI, indicators.INDICADOR_CUMPRIMENTO_TREINAMENTO_JEDI,daoStudent.quantityActivitiesDone(this.userAsana));
            daoStudent.addStudentInList(student);            
        }
        daoStudent.cleanActivity();
        archive.saveLog(daoStudent.getActivityCount(),
                daoStudent.getCountDelayedActivity(),
                daoStudent.getCountActivityDone(),
                userAsana,"skywalkerluke041@gmail.com");
        return userHaveActivity;
    }   
    
    public void deleteDataListForTabel(DefaultTableModel modelTableList){
        int sizeListStudent = daoStudent.getStudents().size();
        for (int row = 0; row < sizeListStudent; row++) {
            modelTableList.removeRow(row);   
        }        
        //return modelTableList;
    }
    
    public DefaultTableModel getDataListForTabel(DefaultTableModel modelTableList){
        //Matricula | Usuário | Equipe_Id | Equipe Nome | Indicador Id | Indicador Nome | Valor | Mes | Ano
        Object[] objects = null;
        int sizeListStudent = daoStudent.getStudents().size();        
        for (int row = 0; row < sizeListStudent; row++) {
            modelTableList.addRow(new Object[]{daoStudent.getStudents().get(row).getRegistration(),
                                               daoStudent.getStudents().get(row).getUser(),
                                               daoStudent.getStudents().get(row).getTeam().getId(),
                                               daoStudent.getStudents().get(row).getTeam().getName(),
                                               daoStudent.getStudents().get(row).getIndicator().getId(),
                                               daoStudent.getStudents().get(row).getIndicator().getName(),
                                               daoStudent.getStudents().get(row).getIndicator().getValue(),
                                               daoStudent.getStudents().get(row).getNumberMonth(),
                                               daoStudent.getStudents().get(row).getNumberYear()});
        }         
        return modelTableList;
    }

    public void exportDataTableForExcelCSV() {        
        archive.exportDataTableForExcelCSV(daoStudent.getStudents());
    }
    
    
}
