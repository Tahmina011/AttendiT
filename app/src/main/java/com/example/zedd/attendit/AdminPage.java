package com.example.zedd.attendit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {
    Button btnAddStudent;
    Button btnAddTeacher;
    Button btnAddCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        btnAddStudent = (Button)findViewById(R.id.btnAddStudent);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,studentRegistration.class);
                startActivity(intent);


            }
        });
        btnAddTeacher = (Button)findViewById(R.id.btnAddTeacher);
        btnAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,RegistrationActivity.class);
                startActivity(intent);


            }
        });
        btnAddCourse = (Button)findViewById(R.id.btnAddCourse);
        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,CourseAct.class);
                startActivity(intent);
            }
        });

    }
}
