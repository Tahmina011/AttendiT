package com.example.zedd.attendit;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class studentprof extends AppCompatActivity {
    TextView sn,si,sb,sp,see,sg,su,sps;
    DatabaseReference mDatabaseReference;
    public List<RTeacher> teach;

    private String np,nid,nbd,nphone,nemail,ngender,nuserID,npassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentprof);
        teach = new ArrayList<>();
        // lvCourses = findViewById(R.id.lvCourses);
        sn=(TextView)findViewById(R.id.sp_name);
        si=(TextView)findViewById(R.id.sp_id);
        sb=(TextView)findViewById(R.id.sp_bd);
        sp=(TextView)findViewById(R.id.sp_phone);
        see=(TextView)findViewById(R.id.sp_eemail);
        sg=(TextView)findViewById(R.id.sp_gender);
        su=(TextView)findViewById(R.id.sp_uid);
        sps=(TextView)findViewById(R.id.sp_pass);
        //LoginActivity u=new LoginActivity();
        Intent intent = getIntent();

        String s =intent.getStringExtra("student_email");
        Query query =  FirebaseDatabase.getInstance().getReference("Students").orderByChild("tEmail").equalTo(s);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // courses.add(new Course("a","b","c"));

                //clearing the previous artist list
                //courses.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    RTeacher cours = postSnapshot.getValue(RTeacher.class);
                    //adding artist to the list
                    teach.add(cours);
                    np=cours.tName;
                    nid=cours.tId;
                    nbd=cours.tBd;
                    nphone=cours.tPhone;
                    nemail=cours.tEmail;
                    ngender=cours.tGender;
                    nuserID=cours.tUserID;
                    npassword=cours.tPassword;
                    sn.setText(np);
                    si.setText(nid);
                    see.setText(nemail);
                    sb.setText(nbd);
                    sg.setText(ngender);
                    su.setText(nuserID);
                    sp.setText(nphone);
                    sps.setText(npassword);




                    //creating adapter
                   /* CoursesAdapter coursesAdapter = new CoursesAdapter(MyCoursesActivity.this, courses);
                    //attaching adapter to the listview
                    lvCourses.setAdapter(coursesAdapter);*/

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });



    }
}
