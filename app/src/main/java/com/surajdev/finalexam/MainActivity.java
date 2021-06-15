package com.surajdev.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText Lat, Longi;
    double lat, longi;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    Model model;

    ArrayList<Model> myList = new ArrayList<>();
    Intent i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lat = findViewById(R.id.lati);
        Longi = findViewById(R.id.longi);
        model = new Model();


        sp = getApplicationContext().getSharedPreferences("locations", MODE_PRIVATE);
        edit = sp.edit();

    }

    public void Gp(View view) {
      i = new Intent(this, MapsActivity.class);
        i.putExtra("mylist", myList);
        startActivity(i);



    }

    public void AddMarker(View view) {
        lat = Double.parseDouble(Lat.getText().toString());
        longi = Double.parseDouble(Longi.getText().toString());
        myList.add(new Model(lat,longi));
        Lat.setText("");
        Longi.setText("");


    }
}