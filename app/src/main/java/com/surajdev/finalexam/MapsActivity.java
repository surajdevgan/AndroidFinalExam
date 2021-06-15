package com.surajdev.finalexam;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.surajdev.finalexam.databinding.ActivityMapsBinding;

import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SensorEventListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    Bundle bundle;
    double lat;
    double longi;

    ArrayList<Model> myList;
    Model model;
    LatLng sydney;
    CameraPosition cameraPosition;
    TextView X, Y;



    //Extract the data…


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bundle = getIntent().getExtras();
        lat = bundle.getDouble("lat");
        longi = bundle.getDouble("long");
        model = new Model();
        myList = (ArrayList<Model>) getIntent().getSerializableExtra("mylist");
        Log.w("listval",""+myList);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null)
        {

            Sensor acclerosensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if(acclerosensor!=null)
            {
                sensorManager.registerListener(this,acclerosensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        for(Model m : myList)
        {

            Log.w("lat",""+m.lat);
            Log.w("long",""+m.longi);

            sydney = new LatLng(m.lat, m.longi);
            mMap.addMarker(new MarkerOptions()
                    .position(sydney)
                    .title("Demo"));
        }

        cameraPosition = new CameraPosition.Builder().target(new LatLng(sydney.latitude, sydney.longitude)).zoom(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));






    }

    @Override
    public void onSensorChanged(SensorEvent event) {



        for(Model m : myList)
        {

            Log.w("lat",""+m.lat);
            Log.w("long",""+m.longi);

            sydney = new LatLng(m.lat, m.longi);
            mMap.addMarker(new MarkerOptions()
                    .position(sydney)
                    .title("Demo"));
        }


        if (event.values[0]<= -0.8){
            cameraPosition = new CameraPosition.Builder().target(new LatLng(sydney.latitude, sydney.longitude)).zoom(10).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}