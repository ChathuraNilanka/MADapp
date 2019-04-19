package com.example.chathura.plan;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Lanka on 9/24/2018.
 */

@SuppressLint("ValidFragment")
public class datePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    TextView dateChooser;

    @SuppressLint("ValidFragment")
    public datePicker(View view) {
        dateChooser = (TextView) view;
    }

    public Dialog onCreateDialog(Bundle saved){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,year,mm,dd);

    }

    public void onDateSet(DatePicker view, int yyyy, int mm, int dd){

       int C_year = Calendar.getInstance().get(Calendar.YEAR);
       int C_MM = Calendar.getInstance().get(Calendar.MONTH);
       int C_DD = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
       String date = "";


        if(C_year == yyyy && C_MM < mm){

            date = yyyy+"/"+(mm+1)+"/"+dd;

            if(C_DD <= dd ){
                date = yyyy+"/"+(mm+1)+"/"+dd;
            }
        }

        if(C_year == yyyy && C_MM == mm){

            //date = yyyy+"/"+(mm+1)+"/"+dd;

            if(C_DD <= dd ){
                date = yyyy+"/"+(mm+1)+"/"+dd;
            }
        }




       if(date.equals("")){
           date = "Invalid Date";
       }

       dateChooser.setText(date);

    }


}
