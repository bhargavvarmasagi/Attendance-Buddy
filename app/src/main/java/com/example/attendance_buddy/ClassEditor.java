package com.example.attendance_buddy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ClassEditor extends AppCompatActivity {
    private int classID;
    private Database database;
    private ArrayList<Student> students;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_class_editor);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        EditText class_name = findViewById(R.id.class_name);
        Button add_students = findViewById(R.id.add_students);
        Button create = findViewById(R.id.create);
        Button delete = findViewById(R.id.delete);
        ListView listview = findViewById(R.id.listview);

        database = new Database();

        if (getIntent().hasExtra("Class ID")) {
            class_name.setText(getIntent().getStringExtra("Class Name"));
            classID = getIntent().getIntExtra("Class ID", 0);
            class_name.setEnabled(false);
            create.setVisibility(View.GONE);

        }
        else {
            add_students.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
            classID = database.getNextClassID();
        }
        students=database.getStudents(classID);

        listAdapter = new ListAdapter();
        listview.setAdapter(listAdapter);

        add_students.setOnClickListener(v-> {
            Intent intent = new Intent(ClassEditor.this, StudentEditor.class);
            intent.putExtra("Class ID", classID);
            startActivity(intent);
        });

        create.setOnClickListener(v-> {
            Class c = new Class(database.getNextClassID(), class_name.getText().toString());
            database.addClass(c);
            class_name.setEnabled(false);
            create.setVisibility(View.GONE);
            add_students.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
        });

        delete.setOnClickListener(v-> {
            database.deleteClass(classID);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        students = database.getStudents(classID);
        listAdapter.notifyDataSetChanged();
    }
    private class ListAdapter extends BaseAdapter {

        public ListAdapter() {}

        @Override
        public int getCount() {
            return students.size();
        }

        @Override
        public Student getItem(int i) {
            return students.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"InflateParams", "ViewHolder"})
            View v = inflater.inflate(R.layout.list_item, null);

            TextView text = v.findViewById(R.id.text);
            text.setText(students.get(i).getFirstName()+" "+students.get(i).getLastName());
            text.setOnClickListener(v1-> {
                Intent intent = new Intent(ClassEditor.this, StudentEditor.class);
                intent.putExtra("Class ID", classID);
                intent.putExtra("Student ID", students.get(i).getID());
                startActivity(intent);
            });

            return v;
        }
    }

}