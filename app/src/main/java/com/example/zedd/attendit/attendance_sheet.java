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

public class attendance_sheet extends AppCompatActivity {
    Spinner cours;
    Button addsheet;
    TextView stoptime;
    EditText classco;
    DatabaseReference attendance,classcode;
    LocationManager locationM;
    private static final int REQUEST_LOCATIO = 1;

    double l,ll;
    double distance=123456789023455.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_sheet);
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATIO);
        attendance= FirebaseDatabase.getInstance().getReference("ATTENDANCE");
        classcode=FirebaseDatabase.getInstance().getReference("classcode");
        cours=(Spinner)findViewById(R.id.coursen);
        stoptime=(TextView)findViewById(R.id.timers);
        addsheet=(Button)findViewById(R.id.sheet);
        classco=(EditText)findViewById(R.id.classcode);
        final String cc=cours.getSelectedItem().toString();

        addsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCour();
                new CountDownTimer(3000000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        stoptime.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        stoptime.setText("done!");
                    }
                }.start();
                Query query;
                query = classcode.orderByChild("course").equalTo(cc);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot coodee:dataSnapshot.getChildren())
                        {
                            coodee.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });



    }
    private void addCour(){
        String cour=cours.getSelectedItem().toString();
        String class_code=classco.getText().toString();

        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd hh");
        String formattedDate=dateFormat.format(date);
        attendance=attendance.child(cour).child(formattedDate);
        for(int i=1507001;i<=1507020;i++)
        {
            String srol=Integer.toString(i);
            roll_absent att=new roll_absent(srol,false);
            attendance.child(srol).setValue(att);
        }
        code co =new code(cour,class_code);
        classcode.child(cour).setValue(co);


       // Toast.makeText(this,"Attendance sheet is Ready for "+cour+"!!",Toast.LENGTH_LONG);


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
