package com.gmit.energyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
}
