package com.example.seoultripplanner_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ScheduleActivity extends AppCompatActivity {

    private String sDate, eDate;
    private RelativeLayout relativeLayout;
    private int sDay, sMonth, eDay, eMonth, daysNum;
    Button addButton;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //retrieve the date data from the DatesActivity activity
        Intent intent = getIntent();
        sDate = intent.getStringExtra("startDate");
        eDate = intent.getStringExtra("endDate");

        sMonth = Integer.parseInt(sDate.substring(0, sDate.indexOf("/")), 10);
        sDay = Integer.parseInt(sDate.substring((sDate.indexOf("/") + 1), sDate.indexOf(".")), 10);
        eMonth = Integer.parseInt(eDate.substring(0, eDate.indexOf("/")), 10);
        eDay = Integer.parseInt(eDate.substring((eDate.indexOf("/") + 1), eDate.indexOf(".")), 10);

        //calculate total days
        if (sDay > eDay) {

            //if starting month ends at 30th
            if (sMonth == 4 || sMonth == 9 || sMonth == 11) {
                daysNum = (30 - sDay + eDay + 1);
            }
            //if February
            else if (sMonth == 2) {
                daysNum = (28 - sDay + eDay + 1);
            }
            //if else
            else {
                daysNum = (31 - sDay + eDay + 1);
            }
        } else {
            daysNum = (eDay - sDay + 1);
        }

        //dynamic generation of the UI
        relativeLayout = findViewById(R.id.relativeLayout);
        for (int i = 0; i < daysNum; i++) {
            LinearLayout linearLayout = findViewById(R.id.linear_Layout);

            dateText = new TextView(this);
            if (sMonth == 4 || sMonth == 9 || sMonth == 11) {
                if ((sDay + i) > 30) {
                    int n = (i - 1);
                    dateText.setText("Day " + (i + 1) + ": " + (sMonth + 1) + "/" + (i - n) + ".2023");
                } else {
                    dateText.setText("Day " + (i + 1) + ": " + sMonth + "/" + (sDay + i) + ".2023");
                }
            } else if (sMonth == 2) {
                if ((sDay + i) > 28) {
                    int n = (i - 1);
                    dateText.setText("Day " + (i + 1) + ": " + (sMonth + 1) + "/" + (i - n) + ".2023");
                } else {
                    dateText.setText("Day " + (i + 1) + ": " + sMonth + "/" + (sDay + i) + ".2023");
                }
            } else {
                if ((sDay + i) > 31) {
                    int n = (i - 1);
                    dateText.setText("Day " + (i + 1) + ": " + (sMonth + 1) + "/" + (i - n) + ".2023");
                } else {
                    dateText.setText("Day " + (i + 1) + ": " + sMonth + "/" + (sDay + i) + ".2023");
                }
            }
            linearLayout.addView(dateText); // ==> logic error in variable n.

            addButton = new Button(this);
            addButton.setText("+");
            linearLayout.addView(addButton);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = new Intent(ScheduleActivity.this, MapActivity.class);
                    startActivity(intent2);
                }
            });
        }
    }
}