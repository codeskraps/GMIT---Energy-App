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

public class GasBoilerActivity extends Activity implements OnClickListener {
	private static final String YOUTUBEVIDEO = "http://www.youtube.com/watch?v=MFzYIpXEjDU";
	
	private Button btnGasBoilerOne = null;
	private Button btnGasBoilerTwo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gasboiler);
		
		btnGasBoilerOne = (Button) findViewById(R.id.btnGasBoilerOne);
		btnGasBoilerTwo = (Button) findViewById(R.id.btnGasBoilerTwo);
		
		btnGasBoilerOne.setOnClickListener(this);
		btnGasBoilerTwo.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGasBoilerOne:
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
	        energyApp.setYouTubeVideo(YOUTUBEVIDEO);
			
	        GasBoilerActivity.this.startActivity(new Intent(GasBoilerActivity.this, YouTubeActivity.class));
	        
			break;
		case R.id.btnGasBoilerTwo:
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
    	
    	menu.findItem(R.id.itemGasBoiler).setEnabled(false);

		return super.onPrepareOptionsMenu(menu);
	}

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
					
		switch (item.getItemId()) {
		case R.id.itemSolar:
			
			GasBoilerActivity.this.startActivity(new Intent(GasBoilerActivity.this, SolarActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
		
		case R.id.itemHeatPump:
			
			GasBoilerActivity.this.startActivity(new Intent(GasBoilerActivity.this, HeatPumpActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
			
		case R.id.itemBiomass:
			
			GasBoilerActivity.this.startActivity(new Intent(GasBoilerActivity.this, BiomassActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
		
		case R.id.itemGasBoiler:
			
			GasBoilerActivity.this.startActivity(new Intent(GasBoilerActivity.this, GasBoilerActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
			
		case R.id.itemHeatTransfer:
			
			GasBoilerActivity.this.startActivity(new Intent(GasBoilerActivity.this, HeatTransferActivity.class));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			
			return true;
		
		case R.id.itemHome:
			
			GasBoilerActivity.this.startActivity(new Intent(GasBoilerActivity.this, HomeActivity.class));
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
