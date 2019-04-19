package com.example.chathura.plan;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import Database.DatabaseHelper;

public class Edit_plan extends AppCompatActivity {
    private ModelPlan mp;
    private String travel_type;
    private DatabaseHelper databaseHelper;

    private EditText tTitle, numDays, stDate, endDate, nPerson;
    private Spinner tType;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plan);

        databaseHelper = new DatabaseHelper(this);
        Spinner mySpinner = findViewById(R.id.travelType);

        ArrayAdapter<String> myAdapter = new ArrayAdapter(Edit_plan.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mySpinner.setAdapter(myAdapter);

        EditText txt = (EditText)findViewById(R.id.sDate);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePicker dia  = new datePicker(view);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dia.show(ft,"DatePicker");
            }
        });

        EditText txt1 = (EditText)findViewById(R.id.eDate);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePicker dia1  = new datePicker(view);
                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                dia1.show(ft1,"DatePicker");
            }
        });

        mySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        travel_type = String.valueOf(adapterView.getItemAtPosition(i));
                        //Toast.makeText(Plan_Trip.this, travel_type, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

            Intent intent = getIntent();
            id = intent.getIntExtra("id", 0);
            mp = databaseHelper.displayTrip(id);

            tTitle = findViewById(R.id.title);
            numDays = findViewById(R.id.nDays);
            stDate = findViewById(R.id.sDate);
            endDate = findViewById(R.id.eDate);
            tType = findViewById(R.id.travelType);
            nPerson = findViewById(R.id.nPersons);

            tTitle.setText(mp.getTitle());
            numDays.setText(mp.getNum_days());
            stDate.setText(mp.getStart_date());
            endDate.setText(mp.getEnd_date());
            mySpinner.setSelection(checkPosition());
            nPerson.setText(mp.getNum_person());

    }

    private int checkPosition() {
        int position = 0;
        String type = mp.getTrans_type();
        if("By Bus".equals(type)){
            position = 1;
        } else if("By Train".equals(type)){
            position = 2;
        } else if("By Van".equals(type)){
            position = 3;
        } else if("By Bike".equals(type)){
            position = 4;
        }
        return position;
    }

    public void sendDetails(View v) {
        Intent intent = new Intent(this, Update_details_submit.class);

        tTitle = findViewById(R.id.title);
        numDays = findViewById(R.id.nDays);
        stDate = findViewById(R.id.sDate);
        endDate = findViewById(R.id.eDate);
        tType = findViewById(R.id.travelType);
        nPerson = findViewById(R.id.nPersons);

        String tripTitle = tTitle.getText().toString();
        String noDays = numDays.getText().toString();
        String startDate = stDate.getText().toString();
        String eDate = endDate.getText().toString();
        String noPersons = nPerson.getText().toString();

        if (TextUtils.isEmpty(tripTitle)) {
            tTitle.setError("Please enter title");
            tTitle.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(noDays)) {
            numDays.setError("Please enter number of days");
            numDays.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(startDate)) {
            stDate.setError("Please enter start date");
            stDate.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(eDate)) {
            endDate.setError("Please enter end date");
            endDate.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(noPersons)) {
            nPerson.setError("Please enter number of persons");
            nPerson.requestFocus();
            return;
        }

        intent.putExtra("id", id);
        intent.putExtra("title" , tripTitle);
        intent.putExtra("nDays" , noDays);
        intent.putExtra("sDate" , startDate);
        intent.putExtra("eDate" , eDate);
        intent.putExtra("tType", travel_type);
        intent.putExtra("nPersons" , noPersons);

        startActivity(intent);
    }
}


