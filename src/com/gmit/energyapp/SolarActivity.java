/**
 * GMIT - Energy app
 * Copyright (C) Carles Sentis 2011 <carlesentis@gmail.com>
 *
 * GMIT - Energy app is free software: you can
 * redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later
 * version.
 *  
 * GMIT - Energy app is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *  
 * You should have received a copy of the GNU
 * General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.gmit.energyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class SolarActivity extends Activity implements OnClickListener{
	private static final String TAG = SolarActivity.class.getSimpleName();
	private static final String YOUTUBEVIDEO = "http://www.youtube.com/watch?v=sqTGm60wP4g";
	
	private EnergyData energyData = null;
	private boolean activityPaused;
	
	private Button btnSolarOne = null;
	private Button btnSolarTwo = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d(TAG, "SolarActivity started");
		
		EnergyApplication energyApp = (EnergyApplication) getApplication();
        energyData = energyApp.getEnergyData();
        
		if (energyData.isChkFullscreen()) {
        	
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	    
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.solar);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
		
		activityPaused = false;
		
		btnSolarOne = (Button) findViewById(R.id.btnSolarOne);
		btnSolarTwo = (Button) findViewById(R.id.btnSolarTwo);
		
		btnSolarOne.setOnClickListener(this);
		btnSolarTwo.setOnClickListener(this);
		
		activityPaused = false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		Log.d(TAG, "SolarActivity onResume");
		
		if (energyData.isInvalidate() && activityPaused) {
			
			Log.d(TAG, "SolarActivity onResume isInvalidte");
			
			SolarActivity.this.startActivity(new Intent(SolarActivity.this, SolarActivity.class));
			SolarActivity.this.finish();
			Log.d(TAG, "SolarActivity onResume finish");
		}
		
		activityPaused = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		activityPaused = true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		
		case R.id.btnSolarOne:
			
			energyData.setYouTubeVideo(YOUTUBEVIDEO);
			
	        SolarActivity.this.startActivity(new Intent(SolarActivity.this, YouTubeActivity.class));
			
	        break;
		
		case R.id.btnSolarTwo:
			
			SolarActivity.this.startActivity(new Intent(SolarActivity.this, SolarOverviewActivity.class));
			
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

    @Override
	public boolean onPrepareOptionsMenu(Menu menu) {
    	
    	menu.findItem(R.id.itemSolar).setEnabled(false);

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
					
		if (item.getItemId() == R.id.itemQuit) {
			
			moveTaskToBack(true);
		
		} else {
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
			SolarActivity.this.startActivity(energyApp.getMenuIntent(item, SolarActivity.this));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
		return super.onOptionsItemSelected(item);
	}

}
