package com.gmit.energyapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HeatTransferActivity extends Activity implements OnClickListener {

	private Button btnHeatOne = null;
	private Button btnHeatTwo = null;
	private Button btnHeatThree = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heattransfer);
		
		btnHeatOne = (Button) findViewById(R.id.btnHeatTransferOne);
		btnHeatTwo = (Button) findViewById(R.id.btnHeatTransferTwo);
		btnHeatThree = (Button) findViewById(R.id.btnHeatTransferThree);
		
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
		case R.id.btnHeatTransferOne:
			break;
		case R.id.btnHeatTransferTwo:
			break;
		case R.id.btnHeatTransferThree:
			break;
		}
	}
}
