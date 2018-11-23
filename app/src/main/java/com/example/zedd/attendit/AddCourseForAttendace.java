package com.example.zedd.attendit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddCourseForAttendace extends AppCompatActivity {
    Button addCourse;
    EditText couseName;

    EditText addDdate;
    Button adddat;

    EditText cou;
    EditText roll;
    Button givepre;
    DatabaseReference attendance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_for_attendace);
        attendance= FirebaseDatabase.getInstance().getReference("ATTENDANCE");

        addCourse = (Button)findViewById(R.id.addCourse);
        couseName=(EditText)findViewById(R.id.courseName);
        addDdate=(EditText)findViewById(R.id.setDate);
        adddat=(Button)findViewById(R.id.addDate);
        roll=(EditText)findViewById(R.id.giveattendance);
        cou=(EditText)findViewById(R.id.fixcourseno);
        givepre=(Button)findViewById(R.id.giveatt);

        addCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addCour();
            }
        } );
        adddat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDate();
            }
        });
        givepre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveAttendance();
            }
        });

    }
    private void addCour(){
        String course=couseName.getText().toString().trim();
        if(TextUtils.isEmpty(course))
        {
            Toast.makeText(this,"Please Enter Course No",Toast.LENGTH_LONG);
        }
        else {
            attendCourse att=new attendCourse(course);
            attendance.child(course).setValue(att);
            couseName.setText("");
            Toast.makeText(this,"Attendance sheet is Ready for "+course+"!!",Toast.LENGTH_LONG);

        }
    }
    private void addDate() {
        String checkc = addDdate.getText().toString().trim();
        //Query query = FirebaseDatabase.getInstance().getReference("Attendance").orderByChild(new attendCourse().getCourseNo()).equalTo(checkc);
        if (TextUtils.isEmpty(checkc)) {
            Toast.makeText(this, "Please Enter Course No", Toast.LENGTH_LONG).show();
        }
        else {
           /* if (query.equals(null))
            {
                Toast.makeText(this,"No Attendance Sheet",Toast.LENGTH_LONG).show();
            }
            else {*/

                Calendar cal = Calendar.getInstance();
                Date date=cal.getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd hh");
                String formattedDate=dateFormat.format(date);
                DateAdd d=new DateAdd(formattedDate);
                attendance.child(checkc).child(formattedDate).setValue(d);
                addDdate.setText("");

        }
    }
    private void giveAttendance()
    {
        String takeroll=roll.getText().toString().trim();
        String co=cou.getText().toString().trim();
        if (TextUtils.isEmpty(takeroll))
        {
            Toast.makeText(this,"Give Fingerprint",Toast.LENGTH_LONG).show();
        }
        else {
            Calendar cal = Calendar.getInstance();
            Date date=cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd hh");
            String formattedDate=dateFormat.format(date);
            attendance.child(co).child(formattedDate).child(takeroll).setValue(takeroll);
            roll.setText("");
            cou.setText("");
            Toast.makeText(this,"Attendance taken!!!!!",Toast.LENGTH_LONG).show();
        }
    }
}
