package com.example.chathura.plan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Database.DatabaseHelper;

public class SubmitNew_Trip extends AppCompatActivity {
    ModelPlan mp = new ModelPlan();
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_new__trip);

        dbHelper = new DatabaseHelper(this);

        mp.setTitle(getIntent().getStringExtra("title"));
        mp.setNum_days(getIntent().getStringExtra("nDays"));
        mp.setStart_date(getIntent().getStringExtra("sDate"));
        mp.setEnd_date(getIntent().getStringExtra("eDate"));
        mp.setTrans_type(getIntent().getStringExtra("tType"));
        mp.setNum_person(getIntent().getStringExtra("nPersons"));


        TextView title = findViewById(R.id.tTitle);
        TextView noDays = findViewById(R.id.nDays);
        TextView startDate = findViewById(R.id.sDate);
        TextView endDate = findViewById(R.id.eDate);
        TextView type = findViewById(R.id.travelType);
        TextView noPersons = findViewById(R.id.nPersons);
        title.setText(mp.getTitle());
        noDays.setText(mp.getNum_days());
        startDate.setText(mp.getStart_date());
        endDate.setText(mp.getEnd_date());
        type.setText(mp.getTrans_type());
        noPersons.setText(mp.getNum_person());

        Button addButton;
        addButton = findViewById(R.id.submit);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.addTripDetail(mp.getTitle(), mp.getNum_days(), mp.getStart_date(), mp.getEnd_date(), mp.getTrans_type(), mp.getNum_person());
                Toast.makeText(SubmitNew_Trip.this, "Data Successfully Added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SubmitNew_Trip.this,Plan_Trip.class);
                startActivity(intent);
            }
        });
    }

}
