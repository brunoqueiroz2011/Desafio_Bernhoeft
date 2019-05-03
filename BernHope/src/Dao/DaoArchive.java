/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Models.AsanaCSV;
import Models.Student;
import Useful.DateUserful;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Bruno
 */
public class DaoArchive {

    private ArrayList<AsanaCSV> listdadoscsv;    
    
    public DaoArchive() {
        listdadoscsv = new ArrayList<>();
    }        
    
    public ArrayList<AsanaCSV> getListdadoscsv() {
        return listdadoscsv;
    }
    
    public String readFirtLineFile(String filePath) throws IOException{
        //Ler o arquivo
        BufferedReader reader = null;
        String line = "";
        String csvDivider = ",";
        String result = "";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine())!= null) {
                
                String[] campos = line.split(csvDivider); 
                if (campos.length > 2) {
                    result = checkFieldsFile(campos);   
                }else{
                    result = "Modelo errado";
                }    
                break;
            }
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        
        return result;
        
    }
    
    public ArrayList<AsanaCSV> readAndTreatFile(String filePath, int month) throws IOException, ParseException{        
        //Ler o arquivo
        BufferedReader reader = null;
        String line = "";
        String csvDivider = ",";
        String result = "";        
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine())!= null) {                
                                
                AsanaCSV dadoscsv = new AsanaCSV();
                
                String[] campos = line.split(csvDivider);                
                try {
                    if (Long.parseLong(campos[0]) > 0) {
                        if ((DateUserful.getMonth(campos[1])+1) == month) {
                            dadoscsv.setTaskId(campos[0]);
                            dadoscsv.setCreatedAt(campos[1]);
                            dadoscsv.setCompletedAt(campos[2]);                
                            dadoscsv.setName(campos[4]);
                            dadoscsv.setAssignee(campos[5]);
                            dadoscsv.setAssigneeEmail(campos[6]);
                            dadoscsv.setStartDate(campos[7]);
                            dadoscsv.setDueDate(campos[8]);   
                            listdadoscsv.add(dadoscsv);
                        }                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }              
            }
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        
        return listdadoscsv;
    }
    
    public void dataType(ArrayList<AsanaCSV> listdadoscsv){
        //Adiciona os dados do arquivo no array de alunos
    }       

    private String checkFieldsFile(String [] campos) {
        String result = "";        
        if (campos[0].equals("Task ID")){
            result = "OK";
        }if (campos[1].equals("Created At")){
            result = "OK";
        }if (campos[2].equals("Completed At")){
            result = "OK";
        }if (campos[3].equals("Last Modified")){
            result = "OK";
        }if (campos[4].equals("Name")){
            result = "OK";
        }if (campos[5].equals("Assignee")){
            result = "OK";
        }if (campos[6].equals("Assignee Email")){
            result = "OK";
        }if (campos[7].equals("Start Date")){
            result = "OK";
        }if (campos[8].equals("Due Date")){
            result = "OK";
        }if (campos[9].equals("Tags")){
            result = "OK";
        }if (campos[10].equals("Notes")){
            result = "OK";
        }if (campos[11].equals("Projects")){
            result = "OK";
        }if (campos[12].equals("Parent Task")) {
            result = "OK";
        }
        return result;
    }
    
    public void exportDataTableForExcelCSV(ArrayList<Student> listStudent){                
        int rows = listStudent.size();
        if (rows > 0) {
            JFileChooser select = new JFileChooser();
            File file;
            if (select.showDialog(null, "Exportar Excel CSV") == JFileChooser.APPROVE_OPTION) {
                file = select.getSelectedFile();
                try {
                    FileWriter archive = new FileWriter(file+".csv");
                    PrintWriter saveArchive = new PrintWriter(archive);
                    for (int row = 0; row < rows; row++) {
                        saveArchive.printf("%s;%d;%d;%s;%d;%s;%.2f;%d;%d\n",
                                              listStudent.get(row).getRegistration(),
                                              listStudent.get(row).getUser(),
                                              listStudent.get(row).getTeam().getId(),
                                              listStudent.get(row).getTeam().getName(),
                                              listStudent.get(row).getIndicator().getId(),
                                              listStudent.get(row).getIndicator().getName(),
                                              listStudent.get(row).getIndicator().getValue(),
                                              listStudent.get(row).getNumberMonth(),
                                              listStudent.get(row).getNumberYear());
                    }                   
                    archive.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Por favor tente novamente"+e);                    
                }finally{                    
                    JOptionPane.showMessageDialog(null, "Planilha exportada com sucesso.");                    
                }
            }
        }
    }
    
    public void saveLog(int totalActivities, int amountOverdueActivities, int quantityActivitiesDone, String emailStudent, String emailTeacher) throws IOException, ParseException{
        FileWriter archive = new FileWriter("log.csv");
        PrintWriter saveArchive = new PrintWriter(archive);
        saveArchive.printf("total de atividade,Atividades Atrasadas,Atividades Feitas,Data extracao,email Aluno,email Professor%n");
        saveArchive.printf("%d,%d,%d,%s,%s,%s%n", totalActivities, amountOverdueActivities, quantityActivitiesDone, DateUserful.getDateToday(), emailStudent, emailTeacher);
        archive.close();
    }
    
}
