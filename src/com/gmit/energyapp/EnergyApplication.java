package com.gmit.energyapp;

import android.app.Application;
import android.util.Log;

public class EnergyApplication extends Application {
	private static final String TAG = EnergyApplication.class.getSimpleName();

	private String youTubeVideo = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.d(TAG, "onCreate started");
		
		setYouTubeVideo("");
	}

	public String getYouTubeVideo() {
		Log.d(TAG, "getYouTubeVideo: " + youTubeVideo);
		
		return youTubeVideo;
	}

	public void setYouTubeVideo(String youTubeVideo) {
		Log.d(TAG, "setYouVideo: " + youTubeVideo);
		
		this.youTubeVideo = youTubeVideo;
	}
}
