package com.example.attendance_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class addFaculty extends AppCompatActivity {
    private EditText firstNameEditText, lastNameEditText, mobileEditText, usernameEditText, passwordEditText;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);
        firstNameEditText = findViewById(R.id.firstnameET);
        lastNameEditText = findViewById(R.id.lastnameET);
        mobileEditText = findViewById(R.id.editTextText3);
        usernameEditText = findViewById(R.id.usernmeET);
        passwordEditText = findViewById(R.id.passwdET);
        saveButton = findViewById(R.id.SaveBT);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || mobile.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(addFaculty.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String data = firstName + "," + lastName + "," + mobile + "," + username + "," + password;
                saveData(data);
            }
        });
    }

    private void saveData(String data) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("faculty.txt", MODE_APPEND);
            fos.write(data.getBytes());
            fos.write("\n".getBytes()); // Add newline for next entry
            clearEditTextFields(); // Clear EditText fields after saving
            Toast.makeText(this, "Faculty added successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving faculty data", Toast.LENGTH_SHORT).show();
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
        usernameEditText.getText().clear();
        passwordEditText.getText().clear();
    }
}