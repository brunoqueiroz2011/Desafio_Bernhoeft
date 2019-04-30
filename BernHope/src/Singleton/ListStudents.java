/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import Models.Student;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public final class ListStudents {
    
    private static ListStudents INSTANCE = new ListStudents();

    private ArrayList<Student> students = new ArrayList();
    
    public ListStudents() {        
    }

    public static synchronized ListStudents getInstance(){
        if (INSTANCE == null) {            
            INSTANCE = new ListStudents();
        }
        return INSTANCE;
    }
    
    public void addListStudents(Student student){
        students.add(student);
    }
    
    
    
}
