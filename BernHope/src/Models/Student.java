/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Bruno
 */
public class Student {
    
    private String registration;
    private int user;
    private Team team;
    private Indicator indicator;
    private int numberMonth;
    private int numberYear;

    public Student() {
    }

    public Student(String registration, int user, Team team, Indicator indicator, int numeroMes, int numeroAno) {
        this.registration = registration;
        this.user = user;
        this.team = team;
        this.indicator = indicator;
        this.numberMonth = numeroMes;
        this.numberYear = numeroAno;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public int getNumberMonth() {
        return numberMonth;
    }

    public void setNumberMonth(int numberMonth) {
        this.numberMonth = numberMonth;
    }

    public int getNumberYear() {
        return numberYear;
    }

    public void setNumberYear(int numberYear) {
        this.numberYear = numberYear;
    }

    
    
    
    
}
