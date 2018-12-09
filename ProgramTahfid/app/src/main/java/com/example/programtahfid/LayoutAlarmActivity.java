package com.example.programtahfid;

import java.util.Calendar;

import com.example.programtahfid.R;

import android.support.v7.app.ActionBarActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class LayoutAlarmActivity extends ActionBarActivity {
	AlarmManager alarm_manager;
	TimePicker alarm_timepicker;
	TextView update_text;
	Context context;
	PendingIntent pending_intent;
	Intent my_intent;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alarm);
        this.context = this;
        
        //initialize our alarm manager;
        alarm_manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        
        //initialize time picker
        
        alarm_timepicker = (TimePicker)findViewById(R.id.timePicker);
        
        //initialize out text update box
        update_text = (TextView)findViewById(R.id.text_update);
        
        //create an instance of a calendar
        final Calendar calendar =Calendar.getInstance();
        
      //create an intent to the alarm receiver
		my_intent = new Intent(this.context, AlarmReceiver.class);
        
        //initialize a start button
        Button alarm_on = (Button)findViewById(R.id.start_alarm);
        
        //initialize a stop button
        Button alarm_off = (Button)findViewById(R.id.stop_alarm);
        
        alarm_on.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				//setting calender instance with the hour and minutes that we picked
				// in the time picker
				calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getCurrentHour());
				calendar.set(Calendar.MINUTE, alarm_timepicker.getCurrentMinute());
				
				//get the value of hour and minutes
				int hour = alarm_timepicker.getCurrentHour();
				int minutes = alarm_timepicker.getCurrentMinute();
				
				//convert int into string
				String hour_string = String.valueOf(hour);
				String minute_string = String.valueOf(minutes);
				
				if (minutes < 10){
					minute_string = "0"+ minute_string;
				}
				my_intent.putExtra("extra", "alarm on");
				//method changes the update textbox
				set_alarm_text("Alarm set to : "+ hour_string + " : "+ minute_string);
				
				//create a pendding intent to delay th intent
				//until the specified calender time;
				pending_intent = PendingIntent.getBroadcast(LayoutAlarmActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				//set alarm manager
				alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
								pending_intent);
				
				//put in extras String into my_intent
				//tells the clock that you pressed the alarm on button
				
				
			}
		});
        alarm_off.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				set_alarm_text("alarm off");
				
				//cancle the alarm
				alarm_manager.cancel(pending_intent);
				
				//put extras string into my inten
				//tell the clock that you pressed the alarm off button
				my_intent.putExtra("extra", "alarm off");
				
				//stop the ringtones
				sendBroadcast(my_intent);
			}
		});
        
    }


    protected void set_alarm_text(String output) {
		update_text.setText(output);
		
	}

}
