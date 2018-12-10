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
    Button btnAddCoursestu;
    Button btnAddCoursetea;
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
        btnAddCoursestu = (Button)findViewById(R.id.btnAddCoursestudent);
        btnAddCoursestu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,add_student_course.class);
                startActivity(intent);
            }
        });
        btnAddCoursetea = (Button)findViewById(R.id.btnAddCourseteacher);
        btnAddCoursetea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this,add_teacher_course.class);
                startActivity(intent);
            }
        });


    }
}
