package com.hespera.mobile.event;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class EventOverlayItem extends OverlayItem {

	private final String time;
	private final String tags;
	
	public EventOverlayItem(GeoPoint point, String title, String time, String tags) {
		super(point, title, "Hello World");
		this.time = time;
		this.tags = tags;
	}

	public String getTime() {
		return time;
	}

	public String getTags() {
		return tags;
	}

}
