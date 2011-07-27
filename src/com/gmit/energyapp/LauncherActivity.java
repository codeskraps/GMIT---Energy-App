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
import android.widget.TextView;

public class LauncherActivity extends Activity implements OnClickListener {
	
	private EnergyData energyData = null;
	private boolean activityPaused;
	
	private ImageView img_solar = null;
	private ImageView img_heatpump = null;
	private ImageView img_biomass = null;
	private ImageView img_gasboiler = null;
	private ImageView img_heattransfer = null;
	private ImageView img_about = null;
	
	private TextView txt_solar = null;
	private TextView txt_heatpump = null;
	private TextView txt_biomass = null;
	private TextView txt_gasboiler = null;
	private TextView txt_heattransfer = null;
	private TextView txt_about = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        EnergyApplication energyApp = (EnergyApplication) getApplication();
        energyData = energyApp.getEnergyData();
        
		if (energyData.isChkFullscreen()) {
        	
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	    
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.launcher);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
		
		activityPaused = false;
        
        img_solar = (ImageView) findViewById(R.id.img_solar);
        img_heatpump = (ImageView) findViewById(R.id.img_heatpump);
        img_biomass = (ImageView) findViewById(R.id.img_biomass);
        img_gasboiler = (ImageView) findViewById(R.id.img_gasboiler);
        img_heattransfer = (ImageView) findViewById(R.id.img_heattransfer);
        img_about = (ImageView) findViewById(R.id.img_about);
        
        txt_solar = (TextView) findViewById(R.id.txtLauncherSolar);
        txt_heatpump = (TextView) findViewById(R.id.txtLauncherHeatPump);
        txt_biomass = (TextView) findViewById(R.id.txtLauncherBiomass);
        txt_gasboiler = (TextView) findViewById(R.id.txtLauncherGasBoiler);
        txt_heattransfer = (TextView) findViewById(R.id.txtLauncherHeatTransfer);
        txt_about = (TextView) findViewById(R.id.txtLauncherAbout);
        
        img_solar.setOnClickListener(this);
        img_heatpump.setOnClickListener(this);
        img_biomass.setOnClickListener(this);
        img_gasboiler.setOnClickListener(this);
        img_heattransfer.setOnClickListener(this);
        img_about.setOnClickListener(this);
        
        txt_solar.setOnClickListener(this);
        txt_heatpump.setOnClickListener(this);
        txt_biomass.setOnClickListener(this);
        txt_gasboiler.setOnClickListener(this);
        txt_heattransfer.setOnClickListener(this);
        txt_about.setOnClickListener(this);
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		
		if (energyData.isInvalidate() && activityPaused) {
			
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, LauncherActivity.class));
			LauncherActivity.this.finish();
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
		switch(v.getId()){
		
		case R.id.img_solar:
		case R.id.txtLauncherSolar:
			
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, SolarActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_heatpump:
		case R.id.txtLauncherHeatPump:
			
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, HeatPumpActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_biomass:
		case R.id.txtLauncherBiomass:
			
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, BiomassActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
			
		case R.id.img_gasboiler:
		case R.id.txtLauncherGasBoiler:
			
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, GasBoilerActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
			
		case R.id.img_heattransfer:
		case R.id.txtLauncherHeatTransfer:
			
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, HeatTransferActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_about:
		case R.id.txtLauncherAbout:
			
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, AboutActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
					
    	if (item.getItemId() == R.id.itemQuit) {
			
			moveTaskToBack(true);
		
		} else {
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
			LauncherActivity.this.startActivity(energyApp.getMenuIntent(item, LauncherActivity.this));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
		return super.onOptionsItemSelected(item);
	}
}