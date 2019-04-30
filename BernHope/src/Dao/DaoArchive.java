/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bruno
 */
public class DaoArchive {

    public DaoArchive() {
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
    
    public void readFile(String filePath){
        //Ler o arquivo
    }
    
    public void treatFile(){
        //Adiciona os dados do arquivo no array de alunos
    }
    
    public void quantityActivitiesDone(){
        //Devolve a quantidade total de atividades realizadas
    }
    
    public void amountOverdueActivities(){
        //Devolve a quantidade total de atividades atrasadas
    }
    
    public void totalActivities(){
        //Devolve a quantidade total de atividades cadastrada para o aluno
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
    
}
