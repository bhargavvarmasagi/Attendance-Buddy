package com.example.attendance_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBTN = findViewById(R.id.startBTN);
        startBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onClickstartBTN(v);
            }
        });
    }

    public void onClickstartBTN(View v) {
        Intent ini = new Intent(this, login.class);

        startActivity(ini);
    }
}