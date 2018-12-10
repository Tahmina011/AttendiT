package com.example.zedd.attendit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class student_front_page extends AppCompatActivity {
    private TextView mypro;
    private TextView mycours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_front_page);
        mypro=(TextView)findViewById(R.id.mypro);
        mycours=(TextView)findViewById(R.id.mycours);
        Intent inten=getIntent();
        final String ss =inten.getStringExtra("em");
        mypro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent().setClass(student_front_page.this,
                        studentprof.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                in.putExtra("student_email",ss);
                startActivity(in);

            }
        });
        mycours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent().setClass(student_front_page.this,
                        student_attendance.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //in.putExtra("teacher_email",ss);
                startActivity(inn);

            }
        });

    }
}
