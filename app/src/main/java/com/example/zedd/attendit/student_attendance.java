package com.example.zedd.attendit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
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

public class student_attendance extends AppCompatActivity {
    Spinner cor;
    Button getsheet;
    TextView stoptime;
    EditText getcode;
    String sk;
    DatabaseReference attendance,coding;
    LocationManager locationM;
    private static final int REQUEST_LOCATIO = 1;
    double l,ll;
    double distance=123456789023455.0;
    DatabaseReference statusroll;
    FirebaseAuth mfire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        mfire = FirebaseAuth.getInstance();
        coding=FirebaseDatabase.getInstance().getReference("classcode");
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATIO);
        attendance= FirebaseDatabase.getInstance().getReference("ATTENDANCE");
        cor=(Spinner)findViewById(R.id.cous);
        stoptime=(TextView)findViewById(R.id.time);
        getsheet=(Button)findViewById(R.id.heet);
        getcode=(EditText)findViewById(R.id.clascode);
        statusroll=FirebaseDatabase.getInstance().getReference("status");


        getsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCour();
            }
        });



    }
    private void addCour(){
        String cour=cor.getSelectedItem().toString();
        Query query;
        final String co=getcode.getText().toString();
        final int[] flagg = {0};

        query = coding.orderByChild("course").equalTo(cour);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot codee:dataSnapshot.getChildren())
                {
                    String tcode;
                    code c=new code();
                   c= codee.getValue(code.class);
                   tcode =c.codee;
                   if(tcode.equals(co))
                   {
                       String casee=cor.getSelectedItem().toString();
                       Calendar cal = Calendar.getInstance();
                       Date date=cal.getTime();
                       DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd hh");
                       String formattedDate=dateFormat.format(date);
                       attendance=attendance.child(casee).child(formattedDate);
                       Status stusta=new Status();
                       String Uid = mfire.getCurrentUser().getUid();
                       Query qu= qu=statusroll.orderByChild("uid").equalTo(Uid);

                       qu.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(DataSnapshot dataSnapshot) {
                               for (DataSnapshot postsnapshot:dataSnapshot.getChildren()) {
                                   final Status sta = postsnapshot.getValue(Status.class);
                                   sk = sta.idd;
                                   roll_absent att=new roll_absent(sk,true);
                                   attendance.child(sk).setValue(att);
                                   flagg[0] =1;


                               }
                           }

                           @Override
                           public void onCancelled(DatabaseError databaseError) {

                           }
                       });

                   }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if(flagg[0]==1)
        {
            Toast.makeText(this,"Attendance taken !!",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Attendance fails !!",Toast.LENGTH_LONG).show();
        }




        //


    }

  /*  private void giveAttendance()
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
                reffff= FirebaseDatabase.getInstance().getReference("classLocation");
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
                reffff= FirebaseDatabase.getInstance().getReference("classLocation");
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
                reffff= FirebaseDatabase.getInstance().getReference("classLocation");
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
    }*/

}
