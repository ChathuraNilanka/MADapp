package com.example.chathura.plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DatabaseHelper;

public class Plan_Trip extends AppCompatActivity {
    private ListView listView;
    private ArrayList<ModelPlan> planModelArrayList;
    private CustomAdapter customAdapter;
    private DatabaseHelper databaseHelper;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan__trip);

        listView = (ListView) findViewById(R.id.lv);

        databaseHelper = new DatabaseHelper(this);

        planModelArrayList = databaseHelper.getAllTrips();

        customAdapter = new CustomAdapter(this,planModelArrayList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                System.out.println(planModelArrayList.get(position).getPlan_id());
                Intent intent = new Intent(Plan_Trip.this, ViewSingle_Trip.class);
                intent.putExtra("tId", planModelArrayList.get(position).getPlan_id());
                startActivity(intent);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.addTrip);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plan_Trip.this,AddNew_Trip.class);
                startActivity(intent);
            }
        });
   }
}