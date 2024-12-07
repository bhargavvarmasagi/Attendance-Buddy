package com.example.attendance_buddy;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ViewSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_view_session);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        int classID = getIntent().getIntExtra("Class ID", 0);
        int sessionID = getIntent().getIntExtra("Session ID", 0);
        Database database = new Database();
        Session session = database.getSession(classID, sessionID);
        ArrayList<Student> allStudents = database.getStudents(classID);
        ArrayList<Student> presentStudents = session.getStudents();
        ArrayList<Integer> presentIDs = new ArrayList<>();
        ArrayList<Student> absentStudents = new ArrayList<>();
        for (Student s : presentStudents) {
            presentIDs.add(s.getID());
        }
        for (Student student : allStudents) {
            if (!presentIDs.contains(student.getID())) absentStudents.add(student);
        }

        ListView present = findViewById(R.id.present);
        present.setAdapter(new ListAdapter(presentStudents));
        setListHeight(present);

        ListView absent = findViewById(R.id.absent);
        absent.setAdapter(new ListAdapter(absentStudents));
        setListHeight(absent);

    }

    private class ListAdapter extends BaseAdapter {
        private ArrayList<Student> data;

        public ListAdapter(ArrayList<Student> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Student getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.list_item, null);
            TextView text = v.findViewById(R.id.text);
            text.setText(data.get(i).getFirstName()+" "+data.get(i).getLastName());
            return v;
        }
    }
    private void setListHeight(ListView list) {
        android.widget.ListAdapter adapter = list.getAdapter();
        if (adapter == null) {
            return;
        }
        int height = 0;
        for (int i=0;i<adapter.getCount();i++) {
            View listitem = adapter.getView(i, null, list);
            listitem.measure(0, 0);
            height += listitem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams par = list.getLayoutParams();
        par.height = height + (list.getDividerHeight() * (adapter.getCount()-1));
        list.setLayoutParams(par);
        list.requestLayout();
    }
}