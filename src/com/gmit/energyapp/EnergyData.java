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

import android.content.Context;

public class EnergyData {

	private Context context = null;
	private boolean invalidate;
	private String youTubeVideo = null;
	private boolean chkFullscreen;
	
	public EnergyData(Context context) {
		setContext(context);
		setInvalidate(false);
		setYouTubeVideo("");
		setChkFullscreen(false);
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
	public boolean isInvalidate() {
		return invalidate;
	}

	public void setInvalidate(boolean invalidate) {
		this.invalidate = invalidate;
	}

	public String getYouTubeVideo() {
		return youTubeVideo;
	}

	public void setYouTubeVideo(String youTubeVideo) {
		this.youTubeVideo = youTubeVideo;
	}

	public boolean isChkFullscreen() {
		return chkFullscreen;
	}

	public void setChkFullscreen(boolean chkFullscreen) {
		this.chkFullscreen = chkFullscreen;
	}
}
