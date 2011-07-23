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
import android.widget.Button;

public class HeatPumpActivity extends Activity implements OnClickListener {
	private static final String YOUTUBEVIDEO = "http://www.youtube.com/watch?v=g9U1xtW-TEo&playnext=1&list=PL4F286D120FAD18B1";
	
	private Button btnHeatOne = null;
	private Button btnHeatTwo = null;
	private Button btnHeatThree = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heatpump);
		
		btnHeatOne = (Button) findViewById(R.id.btnHeatOne);
		btnHeatTwo = (Button) findViewById(R.id.btnHeatTwo);
		btnHeatThree = (Button) findViewById(R.id.btnHeatThree);
		
		btnHeatOne.setOnClickListener(this);
		btnHeatTwo.setOnClickListener(this);
		btnHeatThree.setOnClickListener(this);
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
	        energyApp.setYouTubeVideo(YOUTUBEVIDEO);
			
	        HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, YouTubeActivity.class));
	        
			break;
			
		case R.id.btnHeatTwo:
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
					
		switch (item.getItemId()) {
		case R.id.itemSolar:
			
			HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, SolarActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
		
		case R.id.itemHeatPump:
			
			HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, HeatPumpActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
			
		case R.id.itemBiomass:
			
			HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, BiomassActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
		
		case R.id.itemGasBoiler:
			
			HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, GasBoilerActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
			
		case R.id.itemHeatTransfer:
			
			HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, HeatTransferActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
		
		case R.id.itemHome:
			
			HeatPumpActivity.this.startActivity(new Intent(HeatPumpActivity.this, HomeActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
			
		case R.id.itemQuit:
			
			finish();
			
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
