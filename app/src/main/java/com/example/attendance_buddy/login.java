package com.example.attendance_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText usernameET, passwordET;
    private Button loginBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.pwdET);
        loginBTN = findViewById(R.id.loginBTN);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                if (username.equals("admin") && password.equals("password")) {
                    Intent intent = new Intent(login.this, index.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}