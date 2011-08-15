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

import java.util.Stack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

public class WebViewActivity extends Activity implements OnClickListener {
	private static final String TAG = WebViewActivity.class.getSimpleName();
	private static final String URL = "http://www.gmit.ie/engineering/mechanical-industrial/index.html";
	private static final String SEARCH = "http://www.google.com";

	private EnergyData energyData = null;
	private boolean activityPaused;
	private boolean webLoading;
	private Stack<String> webHistoryBack = null;
	private Stack<String> webHistoryForward = null;
	
	private WebView webView = null;
	private ScrollView scrlView = null;
	private ImageView btnHome = null;
	private ImageView btnLeft = null;
	private ImageView btnRight = null;
	private ImageView btnRefresh = null;
	private ImageView btnSearch = null;
	private ImageView btnBack = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		energyData = ((EnergyApplication) getApplication()).getEnergyData();
		activityPaused = false;
		webLoading = false;
		webHistoryBack = new Stack<String>();
		webHistoryForward = new Stack<String>();
		
		if (energyData.isChkFullscreen()) {
        	
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
		
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	    requestWindowFeature(Window.FEATURE_PROGRESS);
	    
	    setContentView(R.layout.webview);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.icon);
		setProgressBarIndeterminateVisibility(true);
	    setProgressBarVisibility(true);

		try {
			
			webView = (WebView) findViewById(R.id.webview);
			webView.getSettings().setJavaScriptEnabled(true);
			webView.getSettings().setPluginsEnabled(true);
			webView.getSettings().setBuiltInZoomControls(true);
			webView.getSettings().setUseWideViewPort(true);
			webView.setWebViewClient(new WebViewActivityClient());
			
			webView.setWebChromeClient(new WebChromeClient() {
				public void onProgressChanged(WebView view, int progress) {
					setProgress(progress * 100);
					setTitle("Loading...");
					webLoading = true;
					if(progress == 100) {
						setProgressBarIndeterminateVisibility(false);
						setProgressBarVisibility(false);
						setTitle(view.getTitle());
						btnRefresh.setImageResource(R.drawable.webview_refresh);
						webLoading = false;
					}
				}
			});
			webView.loadUrl(URL);
			
		} catch (Exception e) {
	        Log.e(getClass().getSimpleName(), "Browser: " + e.getMessage());
	        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
	    } 
	
		scrlView = (ScrollView) findViewById(R.id.scrlView);
		scrlView.setVerticalScrollBarEnabled(false);

		btnHome = (ImageView) findViewById(R.id.btnHome);
		btnLeft = (ImageView) findViewById(R.id.btnLeft);
		btnRight = (ImageView) findViewById(R.id.btnRight);
		btnRefresh = (ImageView) findViewById(R.id.btnRefresh);
		btnSearch = (ImageView) findViewById(R.id.btnSearch);
		btnBack = (ImageView) findViewById(R.id.btnBack);
		
		btnHome.setOnClickListener(this);
		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		btnRefresh.setOnClickListener(this);
		btnSearch.setOnClickListener(this);
		btnBack.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (energyData.isInvalidate() && activityPaused) {
			
			WebViewActivity.this.startActivity(new Intent(WebViewActivity.this, WebViewActivity.class));
			WebViewActivity.this.finish();
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			btnRefresh.setImageResource(R.drawable.webview_stop);
			if (!webHistoryBack.empty()) webHistoryForward.push(webHistoryBack.pop());
			btnRight.setImageResource(R.drawable.webview_right);
			if (webHistoryBack.empty()) btnLeft.setImageResource(R.drawable.webview_left_bw);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

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
			WebViewActivity.this.startActivity(energyApp.getMenuIntent(item, WebViewActivity.this));
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private class WebViewActivityClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        btnRefresh.setImageResource(R.drawable.webview_stop);
	        webHistoryBack.push(url);
	        Log.d(TAG, url);
	        btnLeft.setImageResource(R.drawable.webview_left);
	        webHistoryForward.clear();
			btnRight.setImageResource(R.drawable.webview_right_bw);
	        return true;
	    }
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnHome:
			webView.loadUrl(URL);
			btnRefresh.setImageResource(R.drawable.webview_stop);
			webHistoryBack.push(URL);
			webHistoryForward.clear();
			btnRight.setImageResource(R.drawable.webview_right_bw);
			break;
		
		case R.id.btnLeft:
			if (!webHistoryBack.empty()) {
				Log.d(TAG, "webLweft");
				webView.goBack();
				btnRefresh.setImageResource(R.drawable.webview_stop);
				webHistoryForward.push(webHistoryBack.pop());
				btnRight.setImageResource(R.drawable.webview_right);
				if (webHistoryBack.empty()) btnLeft.setImageResource(R.drawable.webview_left_bw);
			}
			break;
		
		case R.id.btnRight:
			if (!webHistoryForward.empty()) {
				webView.loadUrl(webHistoryForward.peek());
				btnRefresh.setImageResource(R.drawable.webview_stop);
				webHistoryBack.push(webHistoryForward.pop());
				btnLeft.setImageResource(R.drawable.webview_left);
				if (webHistoryForward.empty()) btnRight.setImageResource(R.drawable.webview_right_bw);
			}
			break;
		
		case R.id.btnRefresh:
			if (!webLoading) {
				webView.reload();
				btnRefresh.setImageResource(R.drawable.webview_stop);
			} else {
				webView.stopLoading();
			}
			break;
		
		case R.id.btnSearch:
			webView.loadUrl(SEARCH);
			btnRefresh.setImageResource(R.drawable.webview_stop);
			webHistoryBack.push(SEARCH);
			webHistoryForward.clear();
			btnRight.setImageResource(R.drawable.webview_right_bw);
			break;
			
		case R.id.btnBack:
			this.finish();
			overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			break;
		}
	}
}
