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

public class AddNew_Trip extends AppCompatActivity {

    String travel_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__trip);
        Spinner mySpinner = findViewById(R.id.travelType);
//*******************************************************************************
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
//*********************************************************************************
        ArrayAdapter<String> myAdapter = new ArrayAdapter(AddNew_Trip.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mySpinner.setAdapter(myAdapter);

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
    }


    public void sendDetails(View v) {
        Intent intent = new Intent(this, SubmitNew_Trip.class);
        EditText nDays = findViewById(R.id.nDays);
        EditText sDate = findViewById(R.id.sDate);
        EditText eDate = findViewById(R.id.eDate);
        EditText nPersons = findViewById(R.id.nPersons);
        EditText title = findViewById(R.id.tTitle);

        String tripTitle = title.getText().toString().trim();
        String noDays = nDays.getText().toString().trim();
        String startDate = sDate.getText().toString().trim();
        String endDate = eDate.getText().toString().trim();
        String noPersons = nPersons.getText().toString().trim();


        if (TextUtils.isEmpty(tripTitle)) {
            title.setError("Please enter title");
            title.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(noDays)) {
            nDays.setError("Please enter number of days");
            nDays.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(startDate)) {
            sDate.setError("Please enter start date");
            sDate.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(endDate)) {
            eDate.setError("Please enter end date");
            eDate.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(noPersons)) {
            nPersons.setError("Please enter number of persons");
            nPersons.requestFocus();
            return;
        }

        intent.putExtra("title" , tripTitle);
        intent.putExtra("nDays" , noDays);
        intent.putExtra("sDate" , startDate);
        intent.putExtra("eDate" , endDate);
        intent.putExtra("tType", travel_type);
        intent.putExtra("nPersons" , noPersons);

        startActivity(intent);
    }


}
