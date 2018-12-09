package com.example.fragments;

import com.example.programtahfid.LayoutAlarmActivity;
import com.example.programtahfid.MainActivity;
import com.example.programtahfid.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MyHome extends Fragment {
	
	Button masuk,alarm,keutamaan;
	View mView;
	Intent i;
	DrawerLayout mDrawerLayout;
	//final DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
	public void openDrawer(){
	    mDrawerLayout.openDrawer(mDrawerLayout);
	}
    

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mView = inflater.inflate(R.layout.fragment_home,container, false);
		masuk = (Button)mView.findViewById(R.id.Masuk_btn);
		keutamaan = (Button) mView.findViewById(R.id.keutamaan);
        alarm =(Button)mView.findViewById(R.id.alarm_murojaah);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.layout.fragment_home);
 	   	Log.d("My home", "IM in my home");
		//alarm.setOnClickListener(this);
		return mView;
	}
	
    

	//@Override
	//public void onClick(View v) {
		//switch (v.getId()) {
		//case R.id.alarm_murojaah:
			//i = new Intent(getActivity().getApplication(),LayoutAlarmActivity.class);
			//startActivity(i);
            //break;
		//case R.id.keutamaan:
//			MyKeutamaan newFragment = new MyKeutamaan();  
//			Bundle args = new Bundle();  
//			//args.putInt(ArticleFragment.ARG_POSITION, position);  
//			newFragment.setArguments(args);
//
//			FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//			// Replace whatever is in the fragment_container view with this fragment,
//			// and add the transaction to the back stack so the user can navigate back
//			transaction.replace(R.id.main_content, newFragment);  
//			transaction.addToBackStack(null);
//
//			// Commit the transaction
//			transaction.commit();  
			//cast activity to MyActivity so compiler doesn't complain
			//mDrawerLayout.openDrawer(GravityCompat.START);
			//break;
    //default:
      //      break;
        //}
	//}
	

}
