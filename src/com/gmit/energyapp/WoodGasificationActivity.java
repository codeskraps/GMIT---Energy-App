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

public class WoodGasificationActivity extends Activity {
	private static final String TAG = WoodGasificationActivity.class.getSimpleName();
	
	private EnergyData energyData = null;
	private boolean activityPaused;
	private boolean showToast;
	
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
		setContentView(R.layout.webview);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
		

		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setUseWideViewPort(true);
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
		
		if (energyData.isChkShowOverviewPins()) 
			webView.loadUrl("file:///android_asset/solar/solar_panels_system_overview_pins.html");
		
		else webView.loadUrl("file:///android_asset/solar/solar_panels_system_overview.html");
		
		activityPaused = false;
		showToast = true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		Log.d(TAG, "WoodGasificationActivity onResume");
		
		if (energyData.isInvalidate() && activityPaused) {
			
			Log.d(TAG, "WoodGasificationActivity onResume isInvalidte");
			
			WoodGasificationActivity.this.startActivity(new Intent(WoodGasificationActivity.this, WoodGasificationActivity.class));
			WoodGasificationActivity.this.finish();
			Log.d(TAG, "WoodGasificationActivity onResume finish");
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
    	
    	menu.findItem(R.id.itemBiomass).setEnabled(false);

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
					
		if (item.getItemId() == R.id.itemQuit) {
			
			moveTaskToBack(true);
		
		} else {
			
			EnergyApplication energyApp = (EnergyApplication) getApplication();
			WoodGasificationActivity.this.startActivity(energyApp.getMenuIntent(item, WoodGasificationActivity.this));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private class WebViewActivityClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	    	Log.d(TAG, "url: " + url);
	    	
	    	if (url.equals("file:///android_asset/solar/1")) showDialog(1);
	    	else if (url.equals("file:///android_asset/solar/2")) showDialog(2);
	    	else if (url.equals("file:///android_asset/solar/3")) showDialog(3);
	    	else if (url.equals("file:///android_asset/solar/4")) showDialog(4);
	    	else if (url.equals("file:///android_asset/solar/5")) showDialog(5);
	    	else if (url.equals("file:///android_asset/solar/6")) showDialog(6);
	    	else if (url.equals("file:///android_asset/solar/7")) showDialog(7);
	    	else if (url.equals("file:///android_asset/solar/8")) showDialog(8);
	    	else if (url.equals("file:///android_asset/solar/9")) showDialog(9);
	    	else if (url.equals("file:///android_asset/solar/10")) showDialog(10);
	    		
	        return true;
	    }
	}

	@Override
    protected Dialog onCreateDialog(int id) {
       	Log.d(TAG, "onCreateDialog 1");
       	
       	switch (id) {
       	case 1:
       		return new AlertDialog.Builder(WoodGasificationActivity.this)
            //.setIcon(R.drawable.solar_bw)
       		.setTitle(getString(R.string.woodgasificationTitlePin1))
            .setMessage(getString(R.string.woodgasificationMessagePin1))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .create();
       		
       	case 2:
       		return new AlertDialog.Builder(WoodGasificationActivity.this)
            //.setIcon(R.drawable.solar_bw)
       		.setTitle(getString(R.string.woodgasificationTitlePin2))
            .setMessage(getString(R.string.woodgasificationMessagePin2))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .create();
       		
       	case 3:
       		return new AlertDialog.Builder(WoodGasificationActivity.this)
            //.setIcon(R.drawable.alert_dialog_icon)
       		.setTitle(getString(R.string.woodgasificationTitlePin3))
            .setMessage(getString(R.string.woodgasificationMessagePin3))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .create();
       		
       	case 4:
       		return new AlertDialog.Builder(WoodGasificationActivity.this)
            //.setIcon(R.drawable.alert_dialog_icon)
       		.setTitle(getString(R.string.woodgasificationTitlePin4))
            .setMessage(getString(R.string.woodgasificationMessagePin4))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .create();
       		
       	case 5:
       		return new AlertDialog.Builder(WoodGasificationActivity.this)
            //.setIcon(R.drawable.alert_dialog_icon)
       		.setTitle(getString(R.string.woodgasificationTitlePin5))
            .setMessage(getString(R.string.woodgasificationMessagePin5))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .create();
       		
       	case 6:
       		return new AlertDialog.Builder(WoodGasificationActivity.this)
            //.setIcon(R.drawable.alert_dialog_icon)
       		.setTitle(getString(R.string.woodgasificationTitlePin6))
            .setMessage(getString(R.string.woodgasificationMessagePin6))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .create();
       		
       	case 7:
       		return new AlertDialog.Builder(WoodGasificationActivity.this)
            //.setIcon(R.drawable.alert_dialog_icon)
       		.setTitle(getString(R.string.woodgasificationTitlePin7))
            .setMessage(getString(R.string.woodgasificationMessagePin7))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .create();
       		
       	case 8:
       		return new AlertDialog.Builder(WoodGasificationActivity.this)
            //.setIcon(R.drawable.alert_dialog_icon)
       		.setTitle(getString(R.string.woodgasificationTitlePin8))
            .setMessage(getString(R.string.woodgasificationMessagePin8))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .create();
       		
       	case 9:
       		return new AlertDialog.Builder(WoodGasificationActivity.this)
            //.setIcon(R.drawable.alert_dialog_icon)
       		.setTitle(getString(R.string.woodgasificationTitlePin9))
            .setMessage(getString(R.string.woodgasificationMessagePin9))
            .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .create();

       	default:
       		return null;
       	}    	
	}
}
