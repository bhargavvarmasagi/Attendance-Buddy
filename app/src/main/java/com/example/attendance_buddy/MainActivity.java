package com.example.attendance_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Database database;
    private ArrayList<Class> classes;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listview);
        Button newClassButton = findViewById(R.id.new_class);

        newClassButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ClassEditor.class);
            startActivity(intent);
        });

        database = new Database();
        classes = new ArrayList<>();
        listAdapter = new ListAdapter();
        listView.setAdapter(listAdapter);

        fetchClasses();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchClasses();
    }

    private void fetchClasses() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Fetch classes from the database
            ArrayList<Class> fetchedClasses = database.getAllClasses();

            handler.post(() -> {
                // Update UI on the main thread
                classes.clear();
                classes.addAll(fetchedClasses);
                listAdapter.notifyDataSetChanged();
            });
        });
    }

    public class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return classes.size();
        }

        @Override
        public Class getItem(int position) {
            return classes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();
                holder.text = convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Class classItem = classes.get(position);
            holder.text.setText(classItem.getClassName());

            holder.text.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ShowSessions.class);
                intent.putExtra("Class ID", classItem.getID());
                intent.putExtra("Class Name", classItem.getClassName());
                startActivity(intent);
            });

            holder.text.setOnLongClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ClassEditor.class);
                intent.putExtra("Class ID", classItem.getID());
                intent.putExtra("Class Name", classItem.getClassName());
                startActivity(intent);
                return true;
            });

            return convertView;
        }

        class ViewHolder {
            TextView text;
        }
    }
}
