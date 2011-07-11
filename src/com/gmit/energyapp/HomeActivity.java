package com.gmit.energyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends Activity implements OnClickListener {
	
	private ImageView img_solar = null;
	private ImageView img_wind = null;
	private ImageView img_heatpump = null;
	private ImageView img_biomass = null;
	private ImageView img_hrvu = null;
	private ImageView img_about = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        
        img_solar = (ImageView) findViewById(R.id.img_solar);
        img_wind = (ImageView) findViewById(R.id.img_wind);
        img_heatpump = (ImageView) findViewById(R.id.img_heatpump);
        img_biomass = (ImageView) findViewById(R.id.img_biomass);
        img_hrvu = (ImageView) findViewById(R.id.img_hrvu);
        img_about = (ImageView) findViewById(R.id.img_about);
        
        img_solar.setOnClickListener(this);
        img_wind.setOnClickListener(this);
        img_heatpump.setOnClickListener(this);
        img_biomass.setOnClickListener(this);
        img_hrvu.setOnClickListener(this);
        img_about.setOnClickListener(this);
        
    }
    
    @Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
    
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		
		case R.id.img_solar:
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, SolarActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_wind:
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, WindActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_heatpump:
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, HeatPumpActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_biomass:
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, BiomassActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_hrvu:
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, HrvuActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		
		case R.id.img_about:
			HomeActivity.this.startActivity(new Intent(HomeActivity.this, AboutActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
	}
}