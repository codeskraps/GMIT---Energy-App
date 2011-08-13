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

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

public class EnergyApplication extends Application {
	private static final String TAG = EnergyApplication.class.getSimpleName();
	private static final String CHKFULLSCREEN = "ckbfullscreen";
	private static final String CHKSHOWWELCOME = "chkshowwelcome";
	private static final String CHKOVERVIEWPINS ="chkshowoverviewpins";
	
	private EnergyData energyData = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.d(TAG, "onCreate started");
		
		setEnergyData(new EnergyData(this));
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		boolean chkFullscreen = prefs.getBoolean(CHKFULLSCREEN, true);
		boolean chkShowWelcome = prefs.getBoolean(CHKSHOWWELCOME, true);
		boolean chkShowOverviewPins = prefs.getBoolean(CHKOVERVIEWPINS, true);
		energyData.setChkFullscreen(chkFullscreen);
		energyData.setChkShowWelcome(chkShowWelcome);
		energyData.setChkShowOverviewPins(chkShowOverviewPins);
	}
	
	public Intent getMenuIntent(MenuItem item, Context context) {
		
		switch (item.getItemId()) {
		
			case R.id.itemSolar: 		return new Intent(context, SolarActivity.class);
			case R.id.itemHeatPump:		return new Intent(context, HeatPumpActivity.class);	
			case R.id.itemBiomass:		return new Intent(context, BiomassActivity.class);
			case R.id.itemGasBoiler:	return new Intent(context, GasBoilerActivity.class);
			case R.id.itemHeatTransfer:	return new Intent(context, HeatTransferActivity.class);
//			case R.id.itemHome:			return new Intent(context, HomeActivity.class);
			case R.id.itemAbout:		return new Intent(context, AboutActivity.class);
			case R.id.itemPreference:	return new Intent(context, PreferenceActivity.class);
		}
		return null;
	}

	public EnergyData getEnergyData() {
		return energyData;
	}

	public void setEnergyData(EnergyData energyData) {
		this.energyData = energyData;
	}
}
