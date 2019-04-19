package com.example.chathura.plan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Database.DatabaseHelper;

public class Update_details_submit extends AppCompatActivity {
    private Button btnSubmit;
    private ModelPlan mp;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details_submit);

        databaseHelper = new DatabaseHelper(this);
        mp = new ModelPlan();

        Intent intent = getIntent();
        mp.setPlan_id(intent.getIntExtra("id", 0));
        mp.setTitle(getIntent().getStringExtra("title"));
        mp.setNum_days(getIntent().getStringExtra("nDays"));
        mp.setStart_date(getIntent().getStringExtra("sDate"));
        mp.setEnd_date(getIntent().getStringExtra("eDate"));
        mp.setTrans_type(getIntent().getStringExtra("tType"));
        mp.setNum_person(getIntent().getStringExtra("nPersons"));

        TextView tTitle = findViewById(R.id.title);
        TextView noDays = findViewById(R.id.nDays);
        TextView startDate = findViewById(R.id.sDate);
        TextView endDate = findViewById(R.id.eDate);
        TextView type = findViewById(R.id.travelType);
        TextView noPersons = findViewById(R.id.nPersons);
        tTitle.setText(mp.getTitle());
        noDays.setText(mp.getNum_days());
        startDate.setText(mp.getStart_date());
        endDate.setText(mp.getEnd_date());
        type.setText(mp.getTrans_type());
        noPersons.setText(mp.getNum_person());

        btnSubmit = findViewById(R.id.submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateTrip(mp.getPlan_id(), mp.getTitle(), mp.getNum_days(), mp.getStart_date(), mp.getEnd_date(), mp.getTrans_type(), mp.getNum_person());
                Toast.makeText(Update_details_submit.this, "Data Successfully Updated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Update_details_submit.this, ViewSingle_Trip.class);
                intent.putExtra("tId", mp.getPlan_id());
                startActivity(intent);
            }
        });
    }
}


