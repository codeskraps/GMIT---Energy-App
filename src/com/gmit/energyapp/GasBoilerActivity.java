package com.gmit.energyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GasBoilerActivity extends Activity implements OnClickListener {
	private static final String YOUTUBEVIDEO = "http://www.youtube.com/watch?v=MFzYIpXEjDU";
	
	private EnergyData energyData = null;
	private boolean activityPaused;

	private Button btnGasBoilerOne = null;
	private Button btnGasBoilerTwo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		EnergyApplication energyApp = (EnergyApplication) getApplication();
        energyData = energyApp.getEnergyData();
        
		if (energyData.isChkFullscreen()) {
        	
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	    
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.gasboiler);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
		
		activityPaused = false;
		
		btnGasBoilerOne = (Button) findViewById(R.id.btnGasBoilerOne);
		btnGasBoilerTwo = (Button) findViewById(R.id.btnGasBoilerTwo);
		
		btnGasBoilerOne.setOnClickListener(this);
		btnGasBoilerTwo.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (energyData.isInvalidate() && activityPaused) {
			
			GasBoilerActivity.this.startActivity(new Intent(GasBoilerActivity.this, GasBoilerActivity.class));
			GasBoilerActivity.this.finish();
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGasBoilerOne:
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
	        EnergyData energyData = energyApp.getEnergyData();
	        energyData.setYouTubeVideo(YOUTUBEVIDEO);
			
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
					
    	if (item.getItemId() == R.id.itemQuit) {
			
			moveTaskToBack(true);
		
		} else {
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
			GasBoilerActivity.this.startActivity(energyApp.getMenuIntent(item, GasBoilerActivity.this));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
		return super.onOptionsItemSelected(item);
	}
}
