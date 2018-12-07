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
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class currentlocationcheck extends AppCompatActivity implements View.OnClickListener {

private static final int REQUEST_LOCATION = 1;
        Button button;
        TextView textView;
        Spinner cours;
        LocationManager locationManager;
        String lattitude,longitude;
        DatabaseReference reff;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentlocationcheck);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        textView = (TextView)findViewById(R.id.text_location);
        button = (Button)findViewById(R.id.button_location);
        cours=(Spinner)findViewById(R.id.coursenum);
        reff= FirebaseDatabase.getInstance().getReference("classLocation");

        button.setOnClickListener(this);

        }

@Override
public void onClick(View view) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        getLocation();
        }
        }

private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
        (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

        if (location != null) {
        double latti = location.getLatitude();
        double longi = location.getLongitude();
        lattitude = String.valueOf(latti);
        longitude = String.valueOf(longi);
        String teacher_id="01234";
        //class_teacher_location setloc=new class_teacher_location();
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd hh mm");
        String formattedDate=dateFormat.format(date);
        String c=cours.getSelectedItem().toString();
        class_teacher_location setloc=new class_teacher_location(formattedDate,c,teacher_id,latti,longi);
        reff.child(teacher_id).setValue(setloc);
       /* textView.setText("Your current location is"+ "\n" + "Lattitude = " + lattitude
        + "\n" + "Longitude = " + longitude);*/
                String cityName=null;
                Geocoder gcd = new Geocoder(this,
                        Locale.getDefault());
                List<Address> addresses;
                try {
                        addresses = gcd.getFromLocation(latti, longi, 1);
                        if (addresses.size() > 0)
                                System.out.println(addresses.get(0).getLocality());
                        cityName=addresses.get(0).getLocality();
                } catch (IOException e) {
                        e.printStackTrace();
                }
            Location startPoint=new Location("locationA");
            startPoint.setLatitude(22.8456);
            startPoint.setLongitude(89.5403);

            Location endPoint=new Location("locationA");
            endPoint.setLatitude(latti);
            endPoint.setLongitude(longi);

            double distance=startPoint.distanceTo(endPoint);

                String s = longitude+"\n"+lattitude +
                        "\n\nMy Currrent City is: "+cityName+"\ndistance: "+distance/1000+"km";
                textView.setText(s);

        } else  if (location1 != null) {
        double latti = location1.getLatitude();
        double longi = location1.getLongitude();
        lattitude = String.valueOf(latti);
        longitude = String.valueOf(longi);

       /* textView.setText("Your current location is"+ "\n" + "Lattitude = " + lattitude
        + "\n" + "Longitude = " + longitude);*/
                String cityName=null;
                Geocoder gcd = new Geocoder(this,
                        Locale.getDefault());
                List<Address> addresses;
                try {
                        addresses = gcd.getFromLocation(latti, longi, 1);
                        if (addresses.size() > 0)
                                System.out.println(addresses.get(0).getLocality());
                        cityName=addresses.get(0).getLocality();
                } catch (IOException e) {
                        e.printStackTrace();
                }

                String s = longitude+"\n"+lattitude +
                        "\n\nMy Currrent City is: "+cityName;
                textView.setText(s);


        } else  if (location2 != null) {
        double latti = location2.getLatitude();
        double longi = location2.getLongitude();
        lattitude = String.valueOf(latti);
        longitude = String.valueOf(longi);


       /* textView.setText("Your current location is"+ "\n" + "Lattitude = " + lattitude
        + "\n" + "Longitude = " + longitude);*/
                String cityName=null;
                Geocoder gcd = new Geocoder(this,
                        Locale.getDefault());
                List<Address> addresses;
                try {
                        addresses = gcd.getFromLocation(latti, longi, 1);
                        if (addresses.size() > 0)
                                System.out.println(addresses.get(0).getLocality());
                        cityName=addresses.get(0).getLocality();
                } catch (IOException e) {
                        e.printStackTrace();
                }

                String s = longitude+"\n"+lattitude +
                        "\n\nMy Currrent City is: "+cityName;
                textView.setText(s);


        }else{

        Toast.makeText(this,"Unble to Trace your location", Toast.LENGTH_SHORT).show();

        }
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
}
