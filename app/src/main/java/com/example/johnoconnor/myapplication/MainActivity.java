package com.example.johnoconnor.myapplication;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {


    private TextView tvGeoLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        this.onLocationChanged(null);
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView tvSpeed = (TextView) this.findViewById(R.id.speed);
        tvGeoLocation = (TextView) this.findViewById(R.id.geoLocation);

        if(location==null){
            tvSpeed.setText("-.- mph");
        }
        else
        {
            double mCurrentSpeed = location.getSpeed();
            tvGeoLocation.setText(mCurrentSpeed + " m/s");
            mCurrentSpeed = mCurrentSpeed * 2.23694;
            tvSpeed.setText(mCurrentSpeed + " mph");

            String msg = "New Latitude: " + location.getLatitude()
                   + "New Longitude: " + location.getLongitude();

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
