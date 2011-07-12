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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BiomassActivity extends Activity implements OnClickListener {
	
	private Button btnBiomassOne = null;
	private Button btnBiomassTwo = null;
	private Button btnBiomassThree = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biomass);
		
		btnBiomassOne = (Button) findViewById(R.id.btnBiomassOne);
		btnBiomassTwo = (Button) findViewById(R.id.btnBiomassTwo);
		btnBiomassThree = (Button) findViewById(R.id.btnBiomassThree);
		
		btnBiomassOne.setOnClickListener(this);
		btnBiomassTwo.setOnClickListener(this);
		btnBiomassThree.setOnClickListener(this);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnBiomassOne:
			break;
		case R.id.btnBiomassTwo:
			break;
		case R.id.btnBiomassThree:
			break;
		}
	}
}
