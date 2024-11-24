package com.example.attendance_buddy;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.attendance_buddy.models.AttendanceBean;
import com.example.attendance_buddy.database.DBAdapter;


import java.util.ArrayList;
import java.util.Calendar;

public class AddAttandanceSessionActivity extends Activity {

    private ImageButton date;
    private Calendar cal;
    private int day;
    private int month;
    private int dyear;
    private EditText dateEditText;
    Button submit;
    Button viewAttendance;
    Button viewTotalAttendance;
    Spinner spinnerbranch, spinneryear, spinnerSubject;
    String branch = "cse";
    String year = "SE";
    String subject = "SC";

    private String[] branchString = new String[]{"cse"};
    private String[] yearString = new String[]{"SE", "TE", "BE"};
    private String[] subjectSEString = new String[]{"SC", "MC"};
    private String[] subjectTEString = new String[]{"GT", "CN"};
    private String[] subjectBEString = new String[]{"DS", "NS"};

    private String[] subjectFinal = new String[]{"M3", "DS", "M4", "CN", "M5", "NS"};
    AttendanceSessionBean attendanceSessionBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attandance_session);

        spinnerbranch = findViewById(R.id.spinner1);
        spinneryear = findViewById(R.id.spinneryear);
        spinnerSubject = findViewById(R.id.spinnerSE);

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branchString);
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(adapter_branch);
        spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
                branch = (String) spinnerbranch.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Do nothing
            }
        });

        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearString);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(adapter_year);
        spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
                year = (String) spinneryear.getSelectedItem();
                Toast.makeText(getApplicationContext(), "Year: " + year, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Do nothing
            }
        });

        ArrayAdapter<String> adapter_subject = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjectFinal);
        adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapter_subject);
        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
                subject = (String) spinnerSubject.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Do nothing
            }
        });

        date = findViewById(R.id.DateImageButton);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        dyear = cal.get(Calendar.YEAR);
        dateEditText = findViewById(R.id.DateEditText);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddAttandanceSessionActivity.this, datePickerListener, dyear, month, day);
                datePickerDialog.show();
            }
        });

        submit = findViewById(R.id.buttonsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
                FacultyBean bean = ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).getFacultyBean();

                attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
                attendanceSessionBean.setAttendance_session_department(branch);
                attendanceSessionBean.setAttendance_session_class(year);
                attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
                attendanceSessionBean.setAttendance_session_subject(subject);

                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
                int sessionId = dbAdapter.addAttendanceSession(attendanceSessionBean);

                ArrayList<StudentBean> studentBeanList = dbAdapter.getAllStudentByBranchYear(branch, year);
                ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).setStudentBeanList(studentBeanList);

                Intent intent = new Intent(AddAttandanceSessionActivity.this, AddAttendanceActivity.class);
                intent.putExtra("sessionId", sessionId);
                startActivity(intent);
            }
        });

        viewAttendance = findViewById(R.id.viewAttendancebutton);
        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
                ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getAttendanceBySessionID(attendanceSessionBean);

                ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
                Intent intent = new Intent(AddAttandanceSessionActivity.this, ViewAttendanceByFacultyActivity.class);
                startActivity(intent);
            }
        });

        viewTotalAttendance = findViewById(R.id.viewTotalAttendanceButton);
        viewTotalAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);
                ArrayList<AttendanceBean> attendanceBeanList = dbAdapter.getTotalAttendanceBySessionID(attendanceSessionBean);

                ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceBeanList(attendanceBeanList);
                Intent intent = new Intent(AddAttandanceSessionActivity.this, ViewAttendanceByFacultyActivity.class);
                startActivity(intent);
            }
        });
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
        }
    };
}
