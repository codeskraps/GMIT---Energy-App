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
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class SolarOverviewActivity extends Activity {
	private static final String TAG = SolarOverviewActivity.class.getSimpleName();
	
//	private static final short DIALOG_PIN_1 = 1;
//	private static final short DIALOG_PIN_2 = 2;
//	private static final short DIALOG_PIN_3 = 3;
//	private static final short DIALOG_PIN_4 = 4;
//	private static final short DIALOG_PIN_5 = 5;
//	private static final short DIALOG_PIN_6 = 6;
//	private static final short DIALOG_PIN_7 = 7;
//	private static final short DIALOG_PIN_8 = 8;
//	private static final short DIALOG_PIN_9 = 9;
//	private static final short DIALOG_PIN_10 = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.solar_overview);
	}

//	@Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//        case DIALOG_PIN_1:
//        	Log.d(TAG, "onCreateDialog 1");
//        	return new AlertDialog.Builder(SolarOverviewActivity.this)
//            //.setIcon(R.drawable.alert_dialog_icon)
//            .setTitle(R.string.solarTitlePin1)
//            .setMessage(R.string.solarMessagePin1)
//            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//
//                    /* User clicked OK so do some stuff */
//                }
//            })
//            .create();
//        }
//        return null;
//	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent started");
		
	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN: 
	        	float x = event.getX();
	            float y = event.getY();
	            
	            Log.d(TAG, "X: " + x + ", Y: " + y);
	            break;
	    }
		return super.onTouchEvent(event);
	}
}
