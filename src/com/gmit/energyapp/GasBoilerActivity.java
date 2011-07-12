package com.gmit.energyapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GasBoilerActivity extends Activity implements OnClickListener {
	
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
			break;
		case R.id.btnGasBoilerTwo:
			break;
		}
	}
}
