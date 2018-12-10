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

public class teacherprofile extends AppCompatActivity {
    TextView tn,ti,tb,tp,tee,tg,tu,tps;
    DatabaseReference mDatabaseReference;
    public List<RTeacher> teach;

    String np,nid,nbd,nphone,nemail,ngender,nuserID,npassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_teacherprofile);
        teach = new ArrayList<>();
        // lvCourses = findViewById(R.id.lvCourses);
        tn=(TextView)findViewById(R.id.mp_name);
        ti=(TextView)findViewById(R.id.mp_id);
        tb=(TextView)findViewById(R.id.mp_bd);
        tp=(TextView)findViewById(R.id.mp_phone);
        tee=(TextView)findViewById(R.id.mp_eemail);
        tg=(TextView)findViewById(R.id.mp_gender);
        tu=(TextView)findViewById(R.id.mp_uid);
        tps=(TextView)findViewById(R.id.mp_pass);
        //LoginActivity u=new LoginActivity();
        Intent intent = getIntent();

       String s =intent.getStringExtra("teacher_email");
        Query query =  FirebaseDatabase.getInstance().getReference("Teachers").orderByChild("tEmail").equalTo(s);

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
                    tn.setText(np);
                    ti.setText(nid);
                    tee.setText(nemail);
                    tb.setText(nbd);
                    tg.setText(ngender);
                    tu.setText(nuserID);
                    tp.setText(nphone);
                    tps.setText(npassword);




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
