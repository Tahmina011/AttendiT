
package com.example.zedd.attendit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class teacher_front_page extends AppCompatActivity {
    private TextView myprof;
    private TextView mycourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_front_page);
        myprof=(TextView)findViewById(R.id.myprof);
        mycourse=(TextView)findViewById(R.id.mycourses);
        Intent inten=getIntent();
        final String ss =inten.getStringExtra("em");
        myprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent().setClass(teacher_front_page.this,
                        teacherprofile.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                in.putExtra("teacher_email",ss);
                startActivity(in);
                finish();
            }
        });
        mycourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent().setClass(teacher_front_page.this,
                        attendance_sheet.class).addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //in.putExtra("teacher_email",ss);
                startActivity(inn);
                finish();
            }
        });

    }
}
