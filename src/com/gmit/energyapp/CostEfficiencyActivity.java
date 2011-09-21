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

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

public class CostEfficiencyActivity extends Activity implements OnClickListener {
	private static final String TAG = CostEfficiencyActivity.class.getSimpleName();
	
	private EnergyData energyData = null;
	private boolean activityPaused;
	
	int iLifeCycleHeatPump;
	int iLifeCyleOilBoiler;
	double iPayBack;
	
	private ScrollView scrollView = null;
	
	private EditText etxtHouseSurface = null;
	private EditText etxtHeating =  null;
	private EditText etxtHotWater =  null;
	private EditText etxtHeatPump = null;
	private EditText etxtBoiler = null;
	private EditText etxtDayRate = null;
	private EditText etxtNightRate = null;
	private EditText etxtOil = null;
	private EditText etxtLifeSpan = null;
	
	private EditText etxtLifeCycleHeatPump = null;
	private EditText etxtLifeCycleBoiler = null;
	private EditText etxtPayBack = null;
	
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
    		
    		scrollView = (ScrollView) findViewById(R.id.costScrollView);
    
            etxtHouseSurface = (EditText) findViewById(R.id.etxt_cost_surface);
            etxtHeating = (EditText) findViewById(R.id.etxt_cost_heating);
            etxtHotWater = (EditText) findViewById(R.id.etxt_cost_hotwater);
            etxtHeatPump = (EditText) findViewById(R.id.etxt_cost_heatpump);
            etxtBoiler = (EditText) findViewById(R.id.etxt_cost_boiler);
            etxtDayRate = (EditText) findViewById(R.id.etxt_cost_dayrate);
            etxtNightRate = (EditText) findViewById(R.id.etxt_cost_nightrate);
            etxtOil = (EditText) findViewById(R.id.etxt_cost_oilcost);
            etxtLifeSpan = (EditText) findViewById(R.id.etxt_cost_lifespan);
            
            etxtLifeCycleHeatPump = (EditText) findViewById(R.id.etxt_cost_life_cycle_heat_pump);
            etxtLifeCycleBoiler = (EditText) findViewById(R.id.etxt_cost_life_cycle_oil_boiler);
            etxtPayBack = (EditText) findViewById(R.id.etxt_cost_payback_period);
            
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
			
            showDialog(1);
            
			break;
			
		case R.id.btnCostEmpty:
			
			showDialog(2);
            
			break;
			
		case R.id.btnCostCalcualte:
			if (calculateCost()) {
				
				Log.d(TAG, "heat pump, oilboiler, payback: " + iLifeCycleHeatPump + ", " + iLifeCyleOilBoiler + ", " + iPayBack);
				
				etxtLifeCycleHeatPump.setText(String.valueOf(iLifeCycleHeatPump));
	            etxtLifeCycleBoiler.setText(String.valueOf(iLifeCyleOilBoiler));
	            etxtPayBack.setText(strPre(iPayBack));
	            
	            scrollView.post(new Runnable() { 
	                public void run() { 
	                    scrollView.fullScroll(ScrollView.FOCUS_DOWN); 
	                } 
	            });
	            
			} else {
				Log.d(TAG, "Something wrong !!!");
				
				showDialog(3);
			}
			break;
		}
	}
	
	private String strPre (double inValue) {
		DecimalFormat threeDec = new DecimalFormat("0.0");
		threeDec.setGroupingUsed(false);
		return threeDec.format(inValue);
	}
	
	private boolean calculateCost () {
		
		int iHouseSurface, iHeating, iHotWater, iHeatPump, iBoiler, iLifeSpan;
		float fDayRate, fNightRate, fOil;
		
		iHouseSurface = iHeating = iHotWater = iHeatPump = iBoiler = iLifeSpan = 0;
		fDayRate = fNightRate = fOil = 0;
				
		try {
			iHouseSurface = Integer.parseInt(etxtHouseSurface.getText().toString());
			iHeating = Integer.parseInt(etxtHeating.getText().toString());
			iHotWater = Integer.parseInt(etxtHotWater.getText().toString());
			iHeatPump = Integer.parseInt(etxtHeatPump.getText().toString());
			iBoiler = Integer.parseInt(etxtBoiler.getText().toString());
			fDayRate = Float.parseFloat(etxtDayRate.getText().toString());
			fNightRate = Float.parseFloat(etxtNightRate.getText().toString());
			fOil = Float.parseFloat(etxtOil.getText().toString());
			iLifeSpan = Integer.parseInt(etxtLifeSpan.getText().toString());
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		
		/*
		 * Calculations
		 */
		int iHeatLoadHouse = (iHouseSurface * iHeating) / 1000;
		int iHeatLoadYear = iHeatLoadHouse * 10 * 7 * 32;
		int iTotalHeatLoadYear = iHeatLoadYear + iHotWater;
		Log.d(TAG, "iHeatLoadHouse: " + iHeatLoadHouse + ", iHeatLoadYear: " + iHeatLoadYear + ", iTotalHeatLoadYear: " + iTotalHeatLoadYear);
		
		// HeatPump
		double iElectricityHeating = iHeatLoadYear / 3.5;
		int iElectricityHotWater = iHotWater / 2;
		double iTotalElectricity = iElectricityHeating + iElectricityHotWater;
		double iHeatPumpYearly = iTotalElectricity / 2 * fDayRate + iTotalElectricity / 2 * fNightRate;
		Log.d(TAG, "iElectricityHeating: " + iElectricityHeating + ", iElectricityHotWater: " + iElectricityHotWater + ", iTotalElectricity: " + iTotalElectricity
				+ ", iHeatPumpYearly: " + iHeatPumpYearly);
		
		// OilBoiler
		double iEnergyHeating = iHeatLoadYear / 0.9;
		double iEnergyHotWater = iHotWater / 0.9;
		double iTotalEnergy = iEnergyHeating + iEnergyHotWater;
		double iOilBoilerYearly = iTotalEnergy * fOil;
		Log.d(TAG, "iEnergyHeating: " + iEnergyHeating + ", iEnergyHotWater: " + iEnergyHotWater + ", iTotalEnergy: " + iTotalEnergy
				+ ", iOilBoilerYearly: " + iOilBoilerYearly);
		
		/*
		 * Results
		 */
		iLifeCycleHeatPump = (int) (iHeatPump + iLifeSpan * iHeatPumpYearly);
		iLifeCyleOilBoiler = (int) (iBoiler + iLifeSpan * iOilBoilerYearly);
		
		iPayBack = iHeatPump / (iOilBoilerYearly - iHeatPumpYearly);
		
		Log.d(TAG, "heat pump, oilboiler, payback: " + iLifeCycleHeatPump + ", " + iLifeCyleOilBoiler + ", " + iPayBack);
		
		return true;
	}
	
	@Override
    protected Dialog onCreateDialog(int id) {
       	Log.d(TAG, "onCreateDialog 1");
       	
       	//Resources res = getResources();
       	//BitmapDrawable icon = new BitmapDrawable(res, myProduct.getUri().toString());
       	       	
       	switch (id) {
       	case 1:
       		return new AlertDialog.Builder(CostEfficiencyActivity.this)
            //.setIcon(R.drawable.solar_bw)
       		.setTitle(getString(R.string.cost_dialog_title_fill))
            .setMessage(getString(R.string.cost_dialog_summary))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

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
                    
                    etxtLifeCycleHeatPump.setText("");
                    etxtLifeCycleBoiler.setText("");
                    etxtPayBack.setText("");
                }
            })
            .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked Cancel so do some stuff */
                }
            })
            .create();
       		
       	case 2:
       		return new AlertDialog.Builder(CostEfficiencyActivity.this)
            //.setIcon(R.drawable.solar_bw)
       		.setTitle(getString(R.string.cost_dialog_title_empty))
            .setMessage(getString(R.string.cost_dialog_summary))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                	etxtHouseSurface.setText("");
                    etxtHeating.setText("");
                    etxtHotWater.setText("");
                    etxtHeatPump.setText("");
                    etxtBoiler.setText("");
                    etxtDayRate.setText("");
                    etxtNightRate.setText("");
                    etxtOil.setText("");
                    etxtLifeSpan.setText("");
                    
                    etxtLifeCycleHeatPump.setText("");
                    etxtLifeCycleBoiler.setText("");
                    etxtPayBack.setText("");
                }
            })
            .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked Cancel so do some stuff */
                }
            })
            .create();
       		
       	case 3:
       		return new AlertDialog.Builder(CostEfficiencyActivity.this)
            //.setIcon(R.drawable.solar_bw)
       		.setTitle(getString(R.string.cost_dialog_title_error))
            .setMessage(getString(R.string.cost_dialog_summary_error))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                	/* User clicked Ok so do some stuff */
                }
            })
            .create();
       		
       	default:
       		return null;
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
