package com.example.zedd.attendit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_teacher_course extends AppCompatActivity {

    DatabaseReference tcoad;
    Spinner tspco;
    EditText tsroll;
    Button tsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_course);
        tspco=(Spinner)findViewById(R.id.teachercourse);
        tsroll=(EditText)findViewById(R.id.teacherid);
        tsa=(Button)findViewById(R.id.teachercouadd);
        tcoad= FirebaseDatabase.getInstance().getReference("teacher_course");
        tsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcourseteacher();
                Intent intent = new Intent(add_teacher_course.this,AdminPage.class);
                startActivity(intent);
            }
        });

    }
    public void addcourseteacher()
    {
        String coursess=tspco.getSelectedItem().toString();
        String sr=tsroll.getText().toString();
        courseTeacherCount q=new courseTeacherCount(coursess,0);
        tcoad.child(sr).child(coursess).setValue(q);
    }
}
