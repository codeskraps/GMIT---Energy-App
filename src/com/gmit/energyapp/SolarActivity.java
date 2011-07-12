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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SolarActivity extends Activity implements OnClickListener{
	private static final String TAG = SolarActivity.class.getSimpleName();
	private static final String YOUTUBEVIDEO = "http://www.youtube.com/watch?v=sqTGm60wP4g";
	
	private Button btnSolarOne = null;
	private Button btnSolarTwo = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d(TAG, "SolarActivity started");
		
		setContentView(R.layout.solar);
		
		btnSolarOne = (Button) findViewById(R.id.btnSolarOne);
		btnSolarTwo = (Button) findViewById(R.id.btnSolarTwo);
		
		btnSolarOne.setOnClickListener(this);
		btnSolarTwo.setOnClickListener(this);
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
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
	        energyApp.setYouTubeVideo(YOUTUBEVIDEO);
			
	        SolarActivity.this.startActivity(new Intent(SolarActivity.this, YouTubeActivity.class));
			
	        break;
		
		case R.id.btnSolarTwo:
			
			SolarActivity.this.startActivity(new Intent(SolarActivity.this, SolarOverviewActivity.class));
			
			break;
		}
		
	}

}
