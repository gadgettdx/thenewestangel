package com.example.johnoconnor.myapplication;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {


    private TextView tvGeoLocation;
    private Button btnSendAText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        this.onLocationChanged(null);
    }

    @Override
    public void onLocationChanged(final Location location) {
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
            btnSendAText = (Button) findViewById(R.id.btnSendText);

            btnSendAText.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view){

                    String phoneNo = "3367720319";
                    String phoneNo1 = "7044908662";
                    String sms = location.getLongitude() + " " + location.getLatitude() + " my location in Charlotte, NC";

                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                        smsManager.sendTextMessage(phoneNo1, null, sms, null, null);

                        Toast.makeText(getApplicationContext(), "SMS Sent!",
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "SMS faild, please try again later!",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            });
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
