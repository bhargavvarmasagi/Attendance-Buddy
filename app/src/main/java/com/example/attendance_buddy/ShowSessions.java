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
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ShowSessions extends AppCompatActivity {

    private int classID;
    private Database database;
    private ArrayList<Session> sessions;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_show_sessions);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ListView listview = findViewById(R.id.listview);

        classID = getIntent().getIntExtra("Class ID", 0);
        database = new Database();
        sessions = database.getSessions(classID);
        listAdapter = new ListAdapter();
        listview.setAdapter(listAdapter);

        Button new_session = findViewById(R.id.new_session);
        new_session.setOnClickListener(v-> {
            Intent intent = new Intent(ShowSessions.this, SessionEditor.class);
            intent.putExtra("Class ID", classID);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sessions = database.getSessions(classID);
        listAdapter.notifyDataSetChanged();
    }

    private class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return sessions.size();
        }

        @Override
        public Session getItem(int i) {
            return sessions.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"InflateParams", "ViewHolder"})
            View v = inflater.inflate(R.layout.list_item, null);
            TextView text = v.findViewById(R.id.text);
            text.setText(sessions.get(i).getSubject() + " - " + sessions.get(i).getDate());

            text.setOnLongClickListener(v1-> {
                Intent intent = new Intent(ShowSessions.this, SessionEditor.class);
                intent.putExtra("Class ID", classID);
                intent.putExtra("Session ID", sessions.get(i).getID());
                startActivity(intent);
                return true;
            });
            text.setOnClickListener(v1-> {
                Intent intent = new Intent(ShowSessions.this, ViewSession.class);
                intent.putExtra("Class ID", classID);
                intent.putExtra("Session ID", sessions.get(i).getID());
                startActivity(intent);
            });

            return v;
        }

    }
}