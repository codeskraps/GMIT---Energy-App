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

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;

public class PreferenceActivity extends android.preference.PreferenceActivity implements OnSharedPreferenceChangeListener {
	private static final String TAG = PreferenceActivity.class.getSimpleName();
	private static final String CHKFULLSCREEN ="ckbfullscreen";
	private static final String CHKSHOWWELCOME = "chkshowwelcome";
	private static final String CHKOVERVIEWPINS ="chkshowoverviewpins";
	
	private EnergyData energyData = null;
	private SharedPreferences prefs = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d(TAG, "Prefs onCreate");
		
		EnergyApplication energyApp = (EnergyApplication) getApplication();
        energyData = energyApp.getEnergyData();
        
		if (energyData.isChkFullscreen()) {
        	
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		
		setTitle(R.string.preference_activity);
		addPreferencesFromResource(R.xml.preferences);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		prefs.unregisterOnSharedPreferenceChangeListener(this);

	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		Log.d(TAG, "Prefs Changed");
		
		if (key.equals(CHKFULLSCREEN)) {
			Log.d(TAG, "Prefs fullscreen Changed");
			
			boolean chkFullscreen = prefs.getBoolean("ckbfullscreen", true);
			energyData.setChkFullscreen(chkFullscreen);
			energyData.setInvalidate(true);
			
			Log.d(TAG, key + ": " + energyData.isChkFullscreen());
			
			
			PreferenceActivity.this.startActivity(new Intent(PreferenceActivity.this, PreferenceActivity.class));
			PreferenceActivity.this.finish();
		
		} else if (key.equals(CHKSHOWWELCOME)) {
			
			boolean chkShowWelcome = prefs.getBoolean(CHKSHOWWELCOME, true);
			energyData.setChkShowWelcome(chkShowWelcome);
		
		} else if (key.equals(CHKOVERVIEWPINS)) {
			
			boolean chkShowOverviewPins = prefs.getBoolean(CHKOVERVIEWPINS, true);
			energyData.setChkShowOverviewPins(chkShowOverviewPins);
			energyData.setInvalidate(true);
		}
	}
}
