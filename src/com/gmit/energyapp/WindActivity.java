package com.gmit.energyapp;

import android.app.Activity;
import android.os.Bundle;

public class WindActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wind_layout);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

}
