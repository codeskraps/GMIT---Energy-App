package com.gmit.energyapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class YouTubeActivity extends Activity {
	private static final String TAG = YouTubeActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d(TAG, "onCreate Started");
		
		EnergyApplication energyApp = (EnergyApplication) getApplication();
        startVideo(energyApp.getYouTubeVideo());
		//sstartVideo("http://www.youtube.com/watch?v=sqTGm60wP4g");
	}

	private void startVideo(String youTubeVideo) {
		Log.d(TAG, "StartVideo started");
		
		Uri uri = Uri.parse(youTubeVideo);
		String vid = uri.getQueryParameter("v");
		
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + vid));
		List<ResolveInfo> list = getPackageManager().queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY);
		
		if (list.size() == 0) {
			startActivity(new Intent(Intent.ACTION_VIEW, uri));
		 }
		
		YouTubeActivity.this.startActivity(i);
		YouTubeActivity.this.finish();
	}
}