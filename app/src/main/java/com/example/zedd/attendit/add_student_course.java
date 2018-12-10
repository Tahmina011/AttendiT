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

public class add_student_course extends AppCompatActivity {
    DatabaseReference coad;
    Spinner spco;
    EditText sroll;
    Button sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_course);
        spco=(Spinner)findViewById(R.id.studentcourse);
        sroll=(EditText)findViewById(R.id.studentRoll);
        sa=(Button)findViewById(R.id.couadd);
        coad= FirebaseDatabase.getInstance().getReference("student_course");
        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcoursestudent();
                Intent intent = new Intent(add_student_course.this,AdminPage.class);
                startActivity(intent);
            }
        });

    }
    public void addcoursestudent()
    {
        String coursess=spco.getSelectedItem().toString();
        String sr=sroll.getText().toString();
        courseStudentCount q=new courseStudentCount(coursess,0);
        coad.child(sr).child(coursess).setValue(q);
    }
}
