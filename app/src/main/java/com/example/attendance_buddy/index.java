package com.example.attendance_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Button logoutBTN = findViewById(R.id.logoutBTN);
        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogoutBTN(v);
            }
        });
        Button addfacultyBTN = findViewById(R.id.addfacultyBTN);
        addfacultyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddFacultyBTN(v);
            }
        });

        Button addStudentBTN = findViewById(R.id.addStudentBTN);
        addStudentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddStudentBTN(v);
            }
        });
        Button viewStudentBTN = findViewById(R.id.viewStudentBTN);
        viewStudentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickViewStudentBTN(v);
            }
        });
        Button viewFacultyBTN = findViewById(R.id.viewFacultyBTN);
        viewFacultyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickViewFacultyBTN(v);
            }
        });
    }

    public void onClickAddFacultyBTN(View v){
        Intent intent = new Intent(this, addFaculty.class);
        startActivity(intent);
    }
    public void onClickAddStudentBTN(View v){
        Intent intent = new Intent(this, addStudent.class );
        startActivity(intent);
    }
    public void onClickViewStudentBTN(View v){
        Intent intent = new Intent(this, viewStudent.class );
        startActivity(intent);
    }
    public void onClickViewFacultyBTN(View v){
        Intent intent = new Intent(this, viewFaculty.class );
        startActivity(intent);
    }

    public void onClickLogoutBTN(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}