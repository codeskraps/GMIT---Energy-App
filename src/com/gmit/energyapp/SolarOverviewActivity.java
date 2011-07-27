/**
 * GMIT - Energy app
 * Copyright (C) Carles Sentis 2011 <carlesentis@gmail.com>
 *
 * GMIT - Energy app is free software: you can
 * redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later
 * version.
 *  
 * GMIT - Energy app is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *  
 * You should have received a copy of the GNU
 * General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.gmit.energyapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class SolarOverviewActivity extends Activity {
	private static final String TAG = SolarOverviewActivity.class.getSimpleName();
	private static final int DIALOG = 1;
	
	private EnergyData energyData = null;
	private boolean activityPaused;
	private boolean showToast;
	private String dialogTitle = null;
	private String dialogMessage = null;
	
	private WebView webView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		EnergyApplication energyApp = (EnergyApplication) getApplication();
        energyData = energyApp.getEnergyData();
        
        
		if (energyData.isChkFullscreen()) {
        	
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.solar_overview);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
		

		webView = (WebView) findViewById(R.id.solar_overview);
		webView.getSettings().setUseWideViewPort(true);
		
		
		//webView.setOnTouchListener(this);
		webView.setWebViewClient(new WebViewActivityClient());
		
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				setProgress(progress * 100);
				if(progress == 100) {
					setProgressBarIndeterminateVisibility(false);
					setProgressBarVisibility(false);
				}
			}
		});
		webView.loadUrl("file:///android_asset/solar/solar_panels_system_overview.html");
		
		activityPaused = false;
		showToast = true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		Log.d(TAG, "SolarOverviewActivity onResume");
		
		if (energyData.isInvalidate() && activityPaused) {
			
			Log.d(TAG, "SolarActivity onResume isInvalidte");
			
			SolarOverviewActivity.this.startActivity(new Intent(SolarOverviewActivity.this, SolarOverviewActivity.class));
			SolarOverviewActivity.this.finish();
			Log.d(TAG, "SolarOverviewActivity onResume finish");
		}
		
		if (showToast) {
			
			Toast toast = Toast.makeText(this, getString(R.string.Zoom), Toast.LENGTH_LONG);
			toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
			toast.show();
			
			showToast = false;
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

//	@Override
//	public boolean onTouch(View v, MotionEvent event) {
//		//Log.d(TAG, "image size Width: " + webView.getWidth() + ", Height: " + webView.getHeight());
//		
//		switch (event.getAction()) {
//			case MotionEvent.ACTION_DOWN: 
//				float x = event.getX();
//				float y = event.getY();
//				
//				int xOffset = v.getScrollX();
//				int yOffset = v.getScrollY();
//				
//				float xSize = webView.getWidth();
//				float ySize = webView.getHeight();
//				float s = webView.getScale();
//				
//				Log.d(TAG, "Image size Width: " + xSize + ", height: " + ySize + ", scale: " + s);
//				Log.d(TAG, "Image scale size: " + (xSize*s) + ", height: " + (ySize*s));
//				Log.d(TAG, "Image Offset   x: " + xOffset + ", y: " + yOffset);
//				Log.d(TAG, "Screen Clicked X: " + x + ", Y: " + y);
//				Log.d(TAG, "Position pin   x: " + (x+xOffset) + ", y: " + (y+yOffset));
//				
//				
//				
//				
//				break; 
//		}
//		return false;
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
					
		if (item.getItemId() == R.id.itemQuit) {
			
			moveTaskToBack(true);
		
		} else {
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
			SolarOverviewActivity.this.startActivity(energyApp.getMenuIntent(item, SolarOverviewActivity.this));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private class WebViewActivityClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	    	Log.d(TAG, "url: " + url);
	    	
	    	if (url.equals("file:///android_asset/solar/1")) {
	    		dialogTitle = getString(R.string.solarTitlePin1);
	    		dialogMessage = getString(R.string.solarMessagePin1);
	    		
	    	} else if (url.equals("file:///android_asset/solar/2")) {
	    		dialogTitle = getString(R.string.solarTitlePin2);
	    		dialogMessage = getString(R.string.solarMessagePin2);
	    		
	    	} else if (url.equals("file:///android_asset/solar/3")) {
	    		dialogTitle = getString(R.string.solarTitlePin3);
	    		dialogMessage = getString(R.string.solarMessagePin3);
	    		
			} else if (url.equals("file:///android_asset/solar/4")) {
				dialogTitle = getString(R.string.solarTitlePin4);
	    		dialogMessage = getString(R.string.solarMessagePin4);
	    		
			} else if (url.equals("file:///android_asset/solar/5")) {
				dialogTitle = getString(R.string.solarTitlePin5);
	    		dialogMessage = getString(R.string.solarMessagePin5);
	    		
			} else if (url.equals("file:///android_asset/solar/6")) {
				dialogTitle = getString(R.string.solarTitlePin6);
	    		dialogMessage = getString(R.string.solarMessagePin6);
	    		
			} else if (url.equals("file:///android_asset/solar/7")) {
				dialogTitle = getString(R.string.solarTitlePin7);
	    		dialogMessage = getString(R.string.solarMessagePin7);
	    		
			} else if (url.equals("file:///android_asset/solar/8")) {
				dialogTitle = getString(R.string.solarTitlePin8);
	    		dialogMessage = getString(R.string.solarMessagePin8);
	    		
			} else if (url.equals("file:///android_asset/solar/9")) {
				dialogTitle = getString(R.string.solarTitlePin9);
	    		dialogMessage = getString(R.string.solarMessagePin9);
	    		
			} else if (url.equals("file:///android_asset/solar/10")) {
				dialogTitle = getString(R.string.solarTitlePin10);
	    		dialogMessage = getString(R.string.solarMessagePin10);
			}
	    	showDialog(DIALOG);
	    	
	        return true;
	    }
	}

	@Override
    protected Dialog onCreateDialog(int id) {
       	Log.d(TAG, "onCreateDialog 1");
    	return new AlertDialog.Builder(SolarOverviewActivity.this)
        //.setIcon(R.drawable.alert_dialog_icon)
        //.setTitle(dialogTitle)
        //.setMessage(dialogMessage)
        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                /* User clicked OK so do some stuff */
            }
        })
        .create();
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
		
		dialog.setTitle(dialogTitle);
		//((AlertDialog) dialog).setTitle(dialogTitle);
		//((AlertDialog) dialog).setMessage(dialogMessage);
	}

}
