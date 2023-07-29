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
        sDay = intent.getIntExtra("sDay", -1);
        sMonth = (intent.getIntExtra("sMonth", -1) + 1);
        eDay = intent.getIntExtra("eDay", -1);
        eMonth = (intent.getIntExtra("eMonth", -1) + 1);

        //calculate total days
        if (sDay > eDay) {

            //if starting month ends at 30th
            if (sMonth == 4 || sMonth == 9 || sMonth == 11) {
                daysNum = (30 - sDay + eDay + 1);
            } else if (sMonth == 2) {
                daysNum = (28 - sDay + eDay + 1);
            } else {
                daysNum = (31 - sDay + eDay + 1);
            }
        } else {
            daysNum = (eDay - sDay + 1);
        }
        // --> logic error?

        //dynamic generation of the UI
        relativeLayout = findViewById(R.id.relativeLayout);
        for (int i = 0; i < 5; i++) {
            LinearLayout linearLayout = findViewById(R.id.linear_Layout);

            dateText = new TextView(this);
            if (sMonth == 4 || sMonth == 9 || sMonth == 11) {
                if ((sDay + i) > 30) {
                    int n = (i - 1);
                    dateText.setText("Day " + (i + 1) + ": 2023." + (sMonth + 1) + "/" + (i - n));
                } else {
                    dateText.setText("Day " + (i + 1) + ": 2023." + sMonth + "/" + (sDay + i));
                }
            } else if (sMonth == 2) {
                if ((sDay + i) > 28) {
                    int n = (i - 1);
                    dateText.setText("Day " + (i + 1) + ": 2023." + (sMonth + 1) + "/" + (i - n));
                } else {
                    dateText.setText("Day " + (i + 1) + ": 2023." + sMonth + "/" + (sDay + i));
                }
            } else {
                if ((sDay + i) > 31) {
                    int n = (i - 1);
                    dateText.setText("Day " + (i + 1) + ": 2023." + (sMonth + 1) + "/" + (i - n));
                } else {
                    dateText.setText("Day " + (i + 1) + ": 2023." + sMonth + "/" + (sDay + i));
                }
            }
            linearLayout.addView(dateText);

            addButton = new Button(this);
            addButton.setText("+");
            linearLayout.addView(addButton);
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ScheduleActivity.this, MapActivity.class);
                startActivity(intent2);
            }
        });
    }
}