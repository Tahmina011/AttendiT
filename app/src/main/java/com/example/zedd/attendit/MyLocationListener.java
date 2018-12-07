package com.example.zedd.attendit;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MyLocationListener implements LocationListener {
    private EditText editLocation = null;
    private ProgressBar pb = null;
    Context con;

    public MyLocationListener(Context context) {
        this.con=context;
    }

    @Override
    public void onLocationChanged(Location loc) {

        editLocation.setText("");
        pb.setVisibility(View.INVISIBLE);
        Toast.makeText(con,"Location changed : Lat: " +
                        loc.getLatitude()+ " Lng: " + loc.getLongitude(),
                Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " +loc.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " +loc.getLatitude();
        Log.v(TAG, latitude);

        /*----------to get City-Name from coordinates ------------- */
        String cityName=null;
        Geocoder gcd = new Geocoder(con,
                Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(loc.getLatitude(), loc
                    .getLongitude(), 1);
            if (addresses.size() > 0)
                System.out.println(addresses.get(0).getLocality());
            cityName=addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = longitude+"\n"+latitude +
                "\n\nMy Currrent City is: "+cityName;
        editLocation.setText(s);
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onStatusChanged(String provider,
                                int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}

