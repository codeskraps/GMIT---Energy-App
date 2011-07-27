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
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener {
	
	private EnergyData energyData = null;
	
	private boolean activityPaused;
	
	private Button btnHomeOne = null;
	private Button btnHomeTwo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		EnergyApplication energyApp = (EnergyApplication) getApplication();
        energyData = energyApp.getEnergyData();
        
		if (energyData.isChkFullscreen()) {
        	
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	    
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.home);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
		
		activityPaused = false;
		
		btnHomeOne = (Button) findViewById(R.id.btnHomeOne);
		btnHomeTwo = (Button) findViewById(R.id.btnHomeTwo);
		
		btnHomeOne.setOnClickListener(this);
		btnHomeTwo.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (energyData.isInvalidate() && activityPaused) {
			
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, HomeActivity.class));
			HomeActivity.this.finish();
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
		case R.id.btnHomeOne:
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, WebViewActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		case R.id.btnHomeTwo:
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, LauncherActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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
    	
    	menu.findItem(R.id.itemHome).setEnabled(false);

		return super.onPrepareOptionsMenu(menu);
	}

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
					
    	if (item.getItemId() == R.id.itemQuit) {
			
			moveTaskToBack(true);
		
		} else {
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
			HomeActivity.this.startActivity(energyApp.getMenuIntent(item, HomeActivity.this));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
		return super.onOptionsItemSelected(item);
	}
}
