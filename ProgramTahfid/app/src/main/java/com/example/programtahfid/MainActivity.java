package com.example.programtahfid;

import java.util.ArrayList;
import java.util.List;

import com.example.adapters.NavListAdapter;
import com.example.fragments.MyAbout;
import com.example.fragments.MyHome;
import com.example.fragments.MyKeutamaan;
import com.example.models.NavItem;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends ActionBarActivity {
	
	DrawerLayout drawerLayout;
	RelativeLayout drawerPane;
	ListView lvNav;
	List<NavItem> listNavItems;
	List<Fragment> listFragments;

	ActionBarDrawerToggle actionBarDrawerToggle;
	//ActionBarDrawerToggle actionBarDrawerToggle;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
		lvNav = (ListView) findViewById(R.id.nav_list);

		listNavItems = new ArrayList<NavItem>();
		listNavItems.add(new NavItem("Home", "MyHome page",
				R.drawable.ic_action_home));
		listNavItems.add(new NavItem("Settings", "Change something",
				R.drawable.ic_action_setting));
		listNavItems.add(new NavItem("About", "Author's information",
				R.drawable.ic_action_about));

		Log.d("MainActivity", "im in my Activity");
		
		NavListAdapter navListAdapter = new NavListAdapter(
				getApplicationContext(), R.layout.item_nav_list, listNavItems);

		lvNav.setAdapter(navListAdapter);

		listFragments = new ArrayList<Fragment>();
		listFragments.add(new MyHome());
		listFragments.add(new MyKeutamaan());
		listFragments.add(new MyAbout());

		// load first fragment as default:
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.main_content, listFragments.get(0)).commit();

		setTitle(listNavItems.get(0).getTitle());
		lvNav.setItemChecked(0, true);
		drawerLayout.closeDrawer(drawerPane);

		// set listener for navigation items:
		lvNav.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// replace the fragment with the selection correspondingly:
				FragmentManager fragmentManager = getSupportFragmentManager();
				fragmentManager
						.beginTransaction()
						.replace(R.id.main_content, listFragments.get(position))
						.commit();

				setTitle(listNavItems.get(position).getTitle());
				lvNav.setItemChecked(position, true);
				drawerLayout.closeDrawer(drawerPane);

			}
		});
		// create listener for drawer layout
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.string.drawer_opened, R.drawable.ic_avatar_mini,R.string.drawer_closed) {

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				invalidateOptionsMenu();
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				invalidateOptionsMenu();
				super.onDrawerClosed(drawerView);
			}

		};

		drawerLayout.setDrawerListener(actionBarDrawerToggle);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the MyHome/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (actionBarDrawerToggle.onOptionsItemSelected(item))
			return true;

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}
}
