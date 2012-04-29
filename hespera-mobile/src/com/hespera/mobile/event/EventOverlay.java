package com.hespera.mobile.event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.hespera.mobile.map.BalloonItemizedOverlay;

public class EventOverlay extends BalloonItemizedOverlay<EventOverlayItem> {

	private List<EventOverlayItem> items = new ArrayList<EventOverlayItem>();
	
	public EventOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenterBottom(defaultMarker), mapView);
		setBalloonBottomOffset(defaultMarker.getBounds().height());
		populate();
	}

	synchronized public void update(List<Event> events) {
		items.clear();
		for(Event event : events) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy h:mm a");
			sdf.setTimeZone(TimeZone.getDefault());
			
			GeoPoint geoPoint = new GeoPoint((int)(event.getLatitude() * 1E6), (int)(event.getLongitude() * 1E6));
			String time = sdf.format(event.getStart());
			String tags = event.getTags().toString();
			items.add(new EventOverlayItem(geoPoint, event.getTitle(), time, tags));
		}
		setLastFocusedIndex(-1);
		populate();
	}
	
	@Override
	synchronized public int size() {
		return items.size();
	}
	
	@Override
	synchronized protected OverlayItem createItem(int i) {
		return items.get(i);
	}
}
