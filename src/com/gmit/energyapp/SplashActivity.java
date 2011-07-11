package com.gmit.energyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 1000;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splash_layout);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
				SplashActivity.this.startActivity(intent);
				SplashActivity.this.finish();
				overridePendingTransition(R.anim.fadein, R.anim.fadeout);

			}

		}, SPLASH_DISPLAY_LENGHT);

	}

}
