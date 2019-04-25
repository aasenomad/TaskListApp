package com.example.tasklist2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        TaskListDB db = new TaskListDB(this);
        StringBuilder sb = new StringBuilder();


        Task task = new Task(1, "Make dentist appointment", "", "0", "0");
        long insertId = db.insertTask(task);
        if (insertId > 0) {
            sb.append("Row inserted! Insert Id: " + insertId + "\n");

        }

        Task task2 = new Task(1, "Take car in for oil change", "", "0", "0");
        long insertId2 = db.insertTask(task2);
        if (insertId2 > 0) {
            sb.append("Row inserted! Insert Id: " + insertId2 + "\n");


        }


        task.setId((int) insertId);
        task.setName("Update test");
        int updateCount = db.updateTask(task);
        if (updateCount == 1){
            sb.append("Task update! Update count: " + updateCount + "\n");
        }

        int deleteCount = db.deleteTask(insertId);
        if ( deleteCount == 1){
            sb.append("Task delete! Delete count: " + deleteCount + "\n\n");
        }

        db.deleteTask(5);
        db.deleteTask(7);

        ArrayList<Task> tasks = db.getTasks("Personal");
        for ( Task t: tasks){
            sb.append(t.getId() + "|" + t.getName() + "\n");

        }

        TextView taskListTextView = (TextView)
                findViewById(R.id.taskListTextView);
        taskListTextView.setText(sb.toString());
    }
}
