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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class HeatPumpActivity extends Activity implements OnClickListener {
	private static final String YOUTUBEVIDEO = "http://www.youtube.com/watch?v=g9U1xtW-TEo&playnext=1&list=PL4F286D120FAD18B1";
	
	private EnergyData energyData = null;
	private boolean activityPaused;
	
	private ImageView btnHeatOne = null;
	private ImageView btnHeatTwo = null;
	private ImageView btnHeatThree = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		EnergyApplication energyApp = (EnergyApplication) getApplication();
        energyData = energyApp.getEnergyData();
        
		if (energyData.isChkFullscreen()) {
        	
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	    
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.heatpump_side_buttons);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
		
		activityPaused = false;
		
		btnHeatOne = (ImageView) findViewById(R.id.btnHeatOne);
		btnHeatTwo = (ImageView) findViewById(R.id.btnHeatTwo);
		btnHeatThree = (ImageView) findViewById(R.id.btnHeatThree);
		
		btnHeatOne.setOnClickListener(this);
		btnHeatTwo.setOnClickListener(this);
		btnHeatThree.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (energyData.isInvalidate() && activityPaused) {
			
			HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, HeatPumpActivity.class));
			HeatPumpActivity.this.finish();
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
		switch (v.getId()) {
		case R.id.btnHeatOne:
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
	        EnergyData energyData = energyApp.getEnergyData();
	        energyData.setYouTubeVideo(YOUTUBEVIDEO);
			
	        HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, YouTubeActivity.class));
	        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	        
			break;
			
		case R.id.btnHeatTwo:
			
			HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, HeatPumpOverviewActivity.class));
	        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	        
			break;
		case R.id.btnHeatThree:
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
    	
    	menu.findItem(R.id.itemHeatPump).setEnabled(false);

		return super.onPrepareOptionsMenu(menu);
	}

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
					
    	if (item.getItemId() == R.id.itemQuit) {
			
			moveTaskToBack(true);
		
		} else {
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
			HeatPumpActivity.this.startActivity(energyApp.getMenuIntent(item, HeatPumpActivity.this));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
		return super.onOptionsItemSelected(item);
	}

}
