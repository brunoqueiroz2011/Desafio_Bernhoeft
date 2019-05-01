/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Models.AsanaCSV;
import Models.Indicator;
import Models.Student;
import Models.Team;
import Singleton.ListStudents;
import Useful.DateUserful;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public class DaoStudent {

    //private ListStudents students;

    //private Student student;      
    
    private ArrayList<Student> students = new ArrayList();

    private ArrayList<AsanaCSV> listdadoscsv;

    private int activityCount;
    private int countDelayedActivity;
    private int countActivityDone;

    public DaoStudent() {
        //this.student = new Student();
        //this.students = new ListStudents();        
    }

    public DaoStudent(ArrayList<AsanaCSV> listdadoscsv) {
        //this.student = new Student();        
        //this.students = new ListStudents();
        this.listdadoscsv = listdadoscsv;
    }

    public Team addNewTeam(int id, String name) {
        Team team = new Team();
        team.setId(id);
        team.setName(name);
        return team;
    }

    public Indicator addNewIndicator(int id, String name, float value) {
        Indicator indicator = new Indicator();
        indicator.setId(id);
        indicator.setName(name);
        indicator.setValue(value);
        return indicator;
    }

    public Student addNewStudent(String registration, int user, int month, int idTeam, String nameTeam, int idIndicator, String nameIndicator, float value) throws ParseException {
        Student student = new Student();
        student.setRegistration(registration);
        student.setUser(user);
        student.setTeam(addNewTeam(idTeam, nameTeam));
        student.setIndicator(addNewIndicator(idIndicator,nameIndicator,value));
        student.setNumberMonth(month);
        student.setNumberYear(DateUserful.getYear(listdadoscsv.get(0).getCreatedAt()));
        return student;
    }
    
    public void addStudentInList(Student student){
        this.students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public float quantityActivitiesDone() {
        float percent = 0;
        //Devolve a quantidade total de atividades realizadas
        for (int line = 0; line < listdadoscsv.size(); line++) {
            if (!listdadoscsv.get(line).getCreatedAt().isEmpty()
                && !listdadoscsv.get(line).getCompletedAt().isEmpty()
                && !listdadoscsv.get(line).getDueDate().isEmpty()) {
                countActivityDone++;
            }
        }         
        
        return percentCalculation(this.countActivityDone, this.activityCount);
    }

    public float amountOverdueActivities() throws ParseException {
        float percent = 0;
        String dateCreate = "", dateComplete = "", deuDate = "";
        long diffDateCSV = 0, diffDateCurrent = 0;
        //Devolve a quantidade total de atividades atrasadas
        for (int line = 0; line < listdadoscsv.size(); line++) {
            if (!listdadoscsv.get(line).getCreatedAt().isEmpty()
                && listdadoscsv.get(line).getCompletedAt().isEmpty()
                && !listdadoscsv.get(line).getDueDate().isEmpty()) {
                
                dateCreate = listdadoscsv.get(line).getCreatedAt();                
                deuDate = listdadoscsv.get(line).getDueDate();
                
                diffDateCSV = DateUserful.getDiffDates(deuDate, dateCreate);
                diffDateCurrent = DateUserful.getDiffDateWithCurrentDate(dateCreate);
                if (diffDateCurrent > diffDateCSV) {
                   countDelayedActivity++;
                }
            }
        }

        return percentCalculation(this.countDelayedActivity, this.activityCount);
    }

    public float percentCalculation(int dividend, float divider){
        float quotient = 0;
        if (divider > 0) {
            quotient = dividend / divider;
        }
        return (quotient*100);
    }
    
    public void totalActivities(String email) {
        //Devolve a quantidade total de atividades cadastrada para o aluno
        for (int line = 0; line < listdadoscsv.size(); line++) {
            if (!listdadoscsv.get(line).getCreatedAt().isEmpty()
                && listdadoscsv.get(line).getAssignee().equals(email)
                && !listdadoscsv.get(line).getDueDate().isEmpty()) {
                this.activityCount++;
            }
        }
    }

}
