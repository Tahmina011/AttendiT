package com.example.zedd.attendit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class course_teacher  extends AppCompatActivity {

    DatabaseReference mDatabaseReference;
    public List<course> courses;

    ListView lvCourses;

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        mDatabaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // courses.add(new Course("a","b","c"));

                //clearing the previous artist list
                //courses.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    course cours = postSnapshot.getValue(course.class);
                    //adding artist to the list
                    courses.add(cours);


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_my_courses);
       // lvCourses = findViewById(R.id.lvCourses);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Courses");
        courses = new ArrayList<>();

    }
}
