package com.example.programtahfid;


import com.example.programtahfid.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class RingtonePlayingService extends Service {

	MediaPlayer media_song;
	int start_id;
	boolean isRinging;
	
	
	  @Override
	    public IBinder onBind(Intent intent) {
	        return null;
	        
	    }

  
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        
        //fetch the extra string values
        String state = intent.getExtras().getString("extra");
        
        Log.d("ringtone state exstra is", state);
        
        //conver extras string into start id value 0 or 1
        assert state != null;
        if(state.equals("alarm on")){
        	startId = 1;
        	Log.d("ringtone state exstra is", String.valueOf(startId));
        }else if (state.equals("alarm off")){
        	startId = 0;
        	Log.d("ringtone state exstra is", String.valueOf(startId));
        }
        
        
        // if else statement
        
        //if there is no music playing and the user pressing on
        //music should start playing
        if(!this.isRinging &&startId==1){
        	media_song = MediaPlayer.create(this, R.raw.dove);
            media_song.start();
            
            this.isRinging = true;
            this.start_id = 0;
        }
        //if there is music playing and the user pressing off
        //music should stop playing
        else if (this.isRinging && startId == 0){
        	
        	// stop the ringtone
        	media_song.stop();
        	media_song.reset();
        	this.isRinging = false;
        	this.start_id = 1;
        }
        
        //these are if the user press the random buttons
        // just bug proof for the app
        //if there is no music plaing and user presses alarm of
        else if (!this.isRinging && startId == 0){
        	
        	this.isRinging = false;
        	this.start_id = 0;
        }
        else if (this.isRinging && startId == 1){
        	this.isRinging = true;
        	this.start_id = 1;
        }
        //cant of anything else, just to catch the odd activity
        else{
        	Log.e("else","somehow you reached this");
        }
        
        
        
        
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Toast.makeText(this,"on Destroy called", Toast.LENGTH_SHORT).show();
    }

   
  
}
