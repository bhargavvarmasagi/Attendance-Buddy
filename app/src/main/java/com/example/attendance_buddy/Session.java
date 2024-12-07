package com.example.attendance_buddy;

import java.util.ArrayList;

public class Session {

    private int ID;
    private ArrayList<Student> students;
    private String subject;
    private String date;

    public Session(){}
    public  int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public void setStudents(ArrayList<Student> students){
        this.students = students;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
