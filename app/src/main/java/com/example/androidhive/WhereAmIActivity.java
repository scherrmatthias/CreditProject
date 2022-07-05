package com.example.androidhive;

import android.app.Activity;



import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.app.ProgressDialog.show;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class WhereAmIActivity extends AppCompatActivity {

    Button btnBack;
    private static final String ERROR_MSG ="Google Play Dienste nicht verfügbar";
    private String first_nameA;
    private String last_nameA;
    private String ageA;
    private String reasonsA;

    private TextView mTextView;
    //TextView: A user interface element thtat displays text to the user.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_am_i);

        Intent intent = getIntent();
        String first_namA = intent.getStringExtra("first_name");
        String last_nameA = intent.getStringExtra("last_name");
        String ageA = intent.getStringExtra("age");
        String reasonsA = intent.getStringExtra("reasons");


        //R: Java class is collection of all resources with its related id
        //id: Whenever you create any resource and assign the id by using @+id (in XML) then R file create on unique id for that resource
        mTextView = findViewById(R.id.myLocationText);

        btnBack = (Button) findViewById(R.id.btnBackInfoEntry);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launching create new product activity
                Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
                startActivity(i);

            }
        });

        GoogleApiAvailability availability = GoogleApiAvailability.getInstance();
        int result = availability.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS){
            if (!availability.isUserResolvableError(result)){
                Toast.makeText(this,ERROR_MSG,Toast.LENGTH_LONG).show();
            }
        }
    }

    private static final int LOCATION_PERMISSION_REQUEST = 1;

    @Override
    protected void onStart(){
        super.onStart();
        //Prüfe die Erlaubnis, auf hochgenaue Ortung zuzugreifen.

        int permission = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);

        //Ist die Erlaubnis erteilt, hole die letzte Position

        if (permission == PERMISSION_GRANTED){
            getLastLocation();
        } else{
            //Ist die Erlaubnis noch nicht erteilt, frage danach.
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST);
            getLastLocation();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults[0] != PERMISSION_GRANTED) {
            //TODO Hier müsste man noch einen Toast wie im Buch machen S. 714
        }
        }
    }

    private void getLastLocation(){
        FusedLocationProviderClient fusedLocationClient;
        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this);

        if(ActivityCompat
                .checkSelfPermission(this,ACCESS_FINE_LOCATION)
                ==PERMISSION_GRANTED ||
                ActivityCompat
                        .checkSelfPermission(this,ACCESS_COARSE_LOCATION)
                        ==PERMISSION_GRANTED){
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>(){
                        @Override
                        public void onSuccess(Location location) {
                            updateTextView(location);
                        }
                    });
        }
    }

    private void updateTextView(Location location){
        String latLongString = "No location found";
        String lat = "No latitude found";
        String lng = "No longitude found";
        if(location != null){
             lat = Double.toString(location.getLatitude());
             lng = Double.toString(location.getLongitude());
            latLongString = "Lat:"+lat+"\nLong:"+lng;
        }
      //  mTextView.setText(latLongString);
        Log.i("Info",latLongString);

        Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
        i.putExtra("first_name",first_nameA);
        i.putExtra("last_name",last_nameA);
        i.putExtra("age",ageA);
        i.putExtra("reasons",reasonsA);
        i.putExtra("lat",lat);
        i.putExtra("lng",lng);

        startActivity(i);

    }



}