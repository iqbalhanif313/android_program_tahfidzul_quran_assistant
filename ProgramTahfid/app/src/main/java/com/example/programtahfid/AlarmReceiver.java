package com.example.programtahfid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		
		
		//fetch extra string from the intent
		String get_your_string = intent.getExtras().getString("extra");
		
		Log.d("what is the key?", get_your_string);
		
		//Create an intent to the ringtone service
		Intent service_intent = new Intent(context, RingtonePlayingService.class);
		
		//pass the extra string from the main activity to ringtone playing service
		service_intent.putExtra("extra", get_your_string);
		
		context.startService(service_intent);
		
	}

}
