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
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

public class CostEfficiencyActivity extends Activity implements OnClickListener {
	
	private EnergyData energyData = null;
	private boolean activityPaused;
	
	private EditText etxtHouseSurface = null;
	private EditText etxtHeating =  null;
	private EditText etxtHotWater =  null;
	private EditText etxtHeatPump = null;
	private EditText etxtBoiler = null;
	private EditText etxtDayRate = null;
	private EditText etxtNightRate = null;
	private EditText etxtOil = null;
	private EditText etxtLifeSpan = null;
	
	private ImageView btnFill = null;
	private ImageView btnEmpty = null;
	private ImageView btnCalculate = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
            EnergyApplication energyApp = (EnergyApplication) getApplication();
            energyData = energyApp.getEnergyData();
            
    		if (energyData.isChkFullscreen()) {
            	
    	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    		}
    	    
    		requestWindowFeature(Window.FEATURE_LEFT_ICON);
            setContentView(R.layout.cost_efficiency);
            setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
            
    		activityPaused = false;
    
            etxtHouseSurface = (EditText) findViewById(R.id.etxt_cost_surface);
            etxtHeating = (EditText) findViewById(R.id.etxt_cost_heating);
            etxtHotWater = (EditText) findViewById(R.id.etxt_cost_hotwater);
            etxtHeatPump = (EditText) findViewById(R.id.etxt_cost_heatpump);
            etxtBoiler = (EditText) findViewById(R.id.etxt_cost_boiler);
            etxtDayRate = (EditText) findViewById(R.id.etxt_cost_dayrate);
            etxtNightRate = (EditText) findViewById(R.id.etxt_cost_nightrate);
            etxtOil = (EditText) findViewById(R.id.etxt_cost_oilcost);
            etxtLifeSpan = (EditText) findViewById(R.id.etxt_cost_lifespan);
            
            btnFill = (ImageView) findViewById(R.id.btnCostFill);
            btnEmpty = (ImageView) findViewById(R.id.btnCostEmpty);
            btnCalculate = (ImageView) findViewById(R.id.btnCostCalcualte);
            
            btnFill.setOnClickListener(this);
            btnEmpty.setOnClickListener(this);
            btnCalculate.setOnClickListener(this);
    }
	
	@Override
	protected void onResume() {
		super.onResume();
			
		if (energyData.isInvalidate() && activityPaused) {
			
			CostEfficiencyActivity.this.startActivity(new Intent(CostEfficiencyActivity.this, CostEfficiencyActivity.class));
			CostEfficiencyActivity.this.finish();
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
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.btnCostFill:
			Resources res = getResources();
			etxtHouseSurface.setText(res.getString(R.string.cost_House_surface_hint));
            etxtHeating.setText(res.getString(R.string.cost_Heat_load_hint));
            etxtHotWater.setText(res.getString(R.string.cost_Heat_load_2_hint));
            etxtHeatPump.setText(res.getString(R.string.cost_Heat_pump_hint));
            etxtBoiler.setText(res.getString(R.string.cost_Boiler_hint));
            etxtDayRate.setText(res.getString(R.string.cost_Electricity_day_hint));
            etxtNightRate.setText(res.getString(R.string.cost_Electricity_night_hint));
            etxtOil.setText(res.getString(R.string.cost_Oil_hint));
            etxtLifeSpan.setText(res.getString(R.string.cost_life_span_hint));
			break;
			
		case R.id.btnCostEmpty:
			etxtHouseSurface.setText("");
            etxtHeating.setText("");
            etxtHotWater.setText("");
            etxtHeatPump.setText("");
            etxtBoiler.setText("");
            etxtDayRate.setText("");
            etxtNightRate.setText("");
            etxtOil.setText("");
            etxtLifeSpan.setText("");
			break;
			
		case R.id.btnCostCalcualte:
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
			CostEfficiencyActivity.this.startActivity(energyApp.getMenuIntent(item, CostEfficiencyActivity.this));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
		return super.onOptionsItemSelected(item);
	}
}
