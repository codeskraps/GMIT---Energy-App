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

public class HomeActivity extends Activity implements OnClickListener {
	
	private Button btnHomeOne = null;
	private Button btnHomeTwo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		btnHomeOne = (Button) findViewById(R.id.btnHomeOne);
		btnHomeTwo = (Button) findViewById(R.id.btnHomeTwo);
		
		btnHomeOne.setOnClickListener(this);
		btnHomeTwo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.btnHomeOne:
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, MechanicalEngineeringDepartmentWebView.class));
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
					
		switch (item.getItemId()) {
		case R.id.itemSolar:
			
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, SolarActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
		
		case R.id.itemHeatPump:
			
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, HeatPumpActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
			
		case R.id.itemBiomass:
			
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, BiomassActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
		
		case R.id.itemGasBoiler:
			
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, GasBoilerActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
			
		case R.id.itemHeatTransfer:
			
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, HeatTransferActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
		
		case R.id.itemHome:
			
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, HomeActivity.class));
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
