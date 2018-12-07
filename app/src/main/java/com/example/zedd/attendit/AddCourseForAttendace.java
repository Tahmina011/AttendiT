package com.example.zedd.attendit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddCourseForAttendace extends AppCompatActivity {
    Button addCourse;
    EditText couseName;

    EditText addDdate;
    Button adddat;

    EditText cou;
    EditText roll;
    Button givepre;
    DatabaseReference attendance,reffff;
    LocationManager locationM;
    private static final int REQUEST_LOCATIO = 1;
    double l,ll;
    double distance=123456789023455.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_for_attendace);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATIO);
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
                locationM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationM.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();

                }
                if (locationM.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getLocation();
                    //giveAttendance();
                }
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
       String  lattitude = String.valueOf(l);
       String longitude = String.valueOf(ll);
        String takeroll=roll.getText().toString().trim();
        String co=cou.getText().toString().trim();
        if (TextUtils.isEmpty(takeroll))
        {
            Toast.makeText(this,"Give Fingerprint",Toast.LENGTH_LONG).show();
        }
        else if(distance < 100.0){
            Calendar cal = Calendar.getInstance();
            Date date=cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd hh");
            String formattedDate=dateFormat.format(date);
            attendance.child(co).child(formattedDate).child(takeroll).setValue(takeroll);
            roll.setText(""+lattitude);
            cou.setText(""+longitude);
            Toast.makeText(this,"Attendance taken!!!!!"+longitude,Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,lattitude+" long distance!!!!!"+longitude,Toast.LENGTH_LONG).show();
        }
    }
    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATIO);

        } else {
            Location location = locationM.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationM.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationM.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latt = location.getLatitude();
                double longd = location.getLongitude();


                String teacher_id="01234";
                reffff=FirebaseDatabase.getInstance().getReference("classLocation");
                class_teacher_location s;
                Query qu;
                qu=reffff.orderByChild("teacher_id");
                qu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        class_teacher_location s=new class_teacher_location();
                        for (DataSnapshot loc : dataSnapshot.getChildren()) {

                            s = loc.getValue(class_teacher_location.class);

                        }
                        l= s.lattii;
                        ll=s.longii;
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Location startPoint=new Location("locationA");
                startPoint.setLatitude(latt);
                startPoint.setLongitude(longd);

                Location endPoint=new Location("locationA");
                endPoint.setLatitude(l);
                endPoint.setLongitude(ll);

                distance=startPoint.distanceTo(endPoint);
                distance=distance/1000.0;


            } else  if (location1 != null) {
                double latt = location.getLatitude();
                double longd = location.getLongitude();


                String teacher_id="01234";
                reffff=FirebaseDatabase.getInstance().getReference("classLocation");
                class_teacher_location s;
                Query qu;
                qu=reffff.orderByChild("teacher_id");
                qu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot loc : dataSnapshot.getChildren()) {
                            class_teacher_location s;
                            s = loc.getValue(class_teacher_location.class);
                            l=s.lattii;
                            ll=s.longii;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                Location startPoint=new Location("locationA");
                startPoint.setLatitude(latt);
                startPoint.setLongitude(longd);

                Location endPoint=new Location("locationA");
                endPoint.setLatitude(l);
                endPoint.setLongitude(ll);

                distance=startPoint.distanceTo(endPoint);
                distance=distance/1000.0;



            } else  if (location2 != null) {
                double latt = location.getLatitude();
                double longd = location.getLongitude();


                String teacher_id="01234";
                reffff=FirebaseDatabase.getInstance().getReference("classLocation");
                class_teacher_location s;
                Query qu;
                qu=reffff.orderByChild("teacher_id");
                qu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot loc : dataSnapshot.getChildren()) {
                            class_teacher_location s;
                            s = loc.getValue(class_teacher_location.class);
                            l=s.lattii;
                            ll=s.longii;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Location startPoint=new Location("locationA");
                startPoint.setLatitude(latt);
                startPoint.setLongitude(longd);

                Location endPoint=new Location("locationA");
                endPoint.setLatitude(l);
                endPoint.setLongitude(ll);

                distance=startPoint.distanceTo(endPoint);
                distance=distance/1000.0;



            }else{

                Toast.makeText(this,"Unble to Trace your location", Toast.LENGTH_SHORT).show();

            }
        }
    }

}
