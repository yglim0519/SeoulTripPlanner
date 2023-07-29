package com.example.seoultripplanner_beta;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Calendar;

public class DatesActivity extends AppCompatActivity {

    private TextView startDate, endDate;
    private DatePickerDialog datePickerDialog;
    private Button nextButton;
    int sDay, sMonth, eDay, eMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        //start date adder
        startDate = findViewById(R.id.startDate);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance(); //calendar instance to get the date from
                int year = calendar.get(Calendar.YEAR); //current year
                int month = calendar.get(Calendar.MONTH); //current month
                int day = calendar.get(Calendar.DAY_OF_MONTH); //current day

                datePickerDialog = new DatePickerDialog(DatesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        startDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();

                //export the chosen day and month values
                sDay = day;
                sMonth = month;
            }
        });

        //end date adder
        endDate = findViewById(R.id.endDate);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance(); //calendar instance to get the date from
                int year = calendar.get(Calendar.YEAR); //current year
                int month = calendar.get(Calendar.MONTH); //current month
                int day = calendar.get(Calendar.DAY_OF_MONTH); //current day

                datePickerDialog = new DatePickerDialog(DatesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        endDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();

                //export the chosen day and month values
                eDay = day;
                eMonth = month;
            }
        });

        //next button
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
            intent.putExtra("startDate", startDate.getText().toString());
            intent.putExtra("endDate", endDate.getText().toString());
            intent.putExtra("sDay", sDay);
            intent.putExtra("sMonth", sMonth);
            intent.putExtra("eDay", eDay);
            intent.putExtra("eMonth", eMonth);
            startActivity(intent);
        });
    }
}