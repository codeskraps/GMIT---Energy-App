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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class LauncherActivity extends Activity implements OnClickListener {
	
	private ImageView img_solar = null;
	private ImageView img_heatpump = null;
	private ImageView img_biomass = null;
	private ImageView img_gasboiler = null;
	private ImageView img_heattransfer = null;
	private ImageView img_about = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);
        
        img_solar = (ImageView) findViewById(R.id.img_solar);
        img_heatpump = (ImageView) findViewById(R.id.img_heatpump);
        img_biomass = (ImageView) findViewById(R.id.img_biomass);
        img_gasboiler = (ImageView) findViewById(R.id.img_gasboiler);
        img_heattransfer = (ImageView) findViewById(R.id.img_heattransfer);
        img_about = (ImageView) findViewById(R.id.img_about);
        
        img_solar.setOnClickListener(this);
        img_heatpump.setOnClickListener(this);
        img_biomass.setOnClickListener(this);
        img_gasboiler.setOnClickListener(this);
        img_heattransfer.setOnClickListener(this);
        img_about.setOnClickListener(this);
        
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
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, SolarActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_heatpump:
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, HeatPumpActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_biomass:
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, BiomassActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
			
		case R.id.img_gasboiler:
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, GasBoilerActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
			
		case R.id.img_heattransfer:
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, HeatTransferActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_about:
			LauncherActivity.this.startActivity(new Intent(LauncherActivity.this, AboutActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
	}
}