package com.example.zedd.attendit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseAct extends AppCompatActivity {
    EditText CT,CN;
    Button sav;
    DatabaseReference cdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        cdata= FirebaseDatabase.getInstance().getReference("Courses");
        CN=(EditText) findViewById(R.id.ec_id);
        CT=(EditText)findViewById(R.id.ec_name);
        sav=(Button)findViewById(R.id.ec_save);
        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourse();
            }
        });

    }

    private void addCourse() {
        String cono=CN.getText().toString().trim();
        String coti=CT.getText().toString().trim();
        if (TextUtils.isEmpty(cono))
        {
            Toast.makeText(this,"Please enter  Course No ",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(coti))
        {
            Toast.makeText(this,"Please enter  Course Title",Toast.LENGTH_LONG).show();
        }
        else
        {
            course cc =new course(cono,coti);
            cdata.child(cono).setValue(cc);
            CT.setText("");
            CN.setText("");
            Toast.makeText(this,"New Course Added",Toast.LENGTH_LONG).show();
        }
    }
}
