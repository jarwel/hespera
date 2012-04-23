package com.hespera.mobile;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.hespera.mobile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button searchButton = (Button)findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				EditText distanceInput = (EditText)findViewById(R.id.distance_input);
				DatePicker dateInput = (DatePicker) findViewById(R.id.date_input); 
				
				Calendar start = new GregorianCalendar(dateInput.getYear(), dateInput.getMonth(), dateInput.getDayOfMonth(), 0, 0, 0);
				Calendar end = new GregorianCalendar(dateInput.getYear(), dateInput.getMonth(), dateInput.getDayOfMonth(), 23, 59, 59);
				Double distance = Double.valueOf(distanceInput.getText().toString());
				
				Intent intent = new Intent(v.getContext(), DisplayMapActivity.class);
				intent.putExtra("start", start.getTimeInMillis());
				intent.putExtra("end", end.getTimeInMillis());
				intent.putExtra("distance", distance);
				
				startActivity(intent);
			}
		}); 	
    }
}