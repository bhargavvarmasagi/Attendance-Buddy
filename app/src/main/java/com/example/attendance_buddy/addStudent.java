package com.example.attendance_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class addStudent extends AppCompatActivity {
    private EditText firstNameEditText, lastNameEditText, mobileEditText, addressEditText, branchEditText;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        firstNameEditText = findViewById(R.id.fnmeET);
        lastNameEditText = findViewById(R.id.lstnmeET);
        mobileEditText = findViewById(R.id.phNoET);
        addressEditText = findViewById(R.id.addrsET);
        branchEditText = findViewById(R.id.branchET);
        saveButton = findViewById(R.id.SaveBTN);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String branch = branchEditText.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || mobile.isEmpty() || address.isEmpty() || branch.isEmpty()) {
                    Toast.makeText(addStudent.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String data = firstName + "," + lastName + "," + mobile + "," + address + "," + branch;
                saveData(data);
            }
        });
    }

    private void saveData(String data) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("students.txt", MODE_APPEND);
            fos.write(data.getBytes());
            fos.write("\n".getBytes()); // Add newline for next entry
            clearEditTextFields(); // Clear EditText fields after saving
            Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving student data", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void clearEditTextFields() {
        firstNameEditText.getText().clear();
        lastNameEditText.getText().clear();
        mobileEditText.getText().clear();
        addressEditText.getText().clear();
        branchEditText.getText().clear();

    }
}