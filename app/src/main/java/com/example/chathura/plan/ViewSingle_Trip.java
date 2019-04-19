package com.example.chathura.plan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import Database.DatabaseHelper;

public class ViewSingle_Trip extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private  ModelPlan mp;
    private int id;

    private TextView trip_title, sTitle, numDays, startDate, endDate, type, numPerson;
    private  Button btnUpdate, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single__trip);

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("tId", 0);

        databaseHelper = new DatabaseHelper(this);
        mp = databaseHelper.displayTrip(id);

       trip_title = findViewById(R.id.trip_title);
       sTitle = findViewById(R.id.tTitle);
       numDays = findViewById(R.id.nDays);
       startDate = findViewById(R.id.sDate);
       endDate = findViewById(R.id.eDate);
       type = findViewById(R.id.travelType);
       numPerson= findViewById(R.id.nPerson);

       trip_title.setText(mp.getTitle());
       sTitle.setText(mp.getTitle());
       numDays.setText(mp.getNum_days());
       startDate.setText(mp.getStart_date());
       endDate.setText(mp.getEnd_date());
       type.setText(mp.getTrans_type());
       numPerson.setText(mp.getNum_person());

        btnUpdate = findViewById(R.id.update);
        btnDelete = findViewById(R.id.delete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            ModelPlan modelplan = mp;
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSingle_Trip.this, Edit_plan.class);
                intent.putExtra("id", modelplan.getPlan_id());
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(ViewSingle_Trip.this);
                alertDialog2.setTitle("Confirm Delete...");
                alertDialog2.setMessage("Are you sure you want delete this trip ?");
                //alertDialog2.setIcon(R.drawable.delete);
                alertDialog2.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                databaseHelper.deleteTrip(id);
                                Toast.makeText(ViewSingle_Trip.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ViewSingle_Trip.this, Plan_Trip.class);
                                startActivity(intent);
                            }
                        });
                alertDialog2.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                alertDialog2.show();

            }
        });

    }
}
