package com.hespera.mobile;

import java.util.Date;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.hespera.mobile.R;
import com.hespera.mobile.client.EventClient;
import com.hespera.mobile.map.EventOverlay;
import com.hespera.mobile.model.Event;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

public class DisplayMapActivity extends MapActivity {

	private EventOverlay eventOverlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.display_map);
	    
	    MapView mapView = (MapView)findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    Drawable defaultMarker = getResources().getDrawable(R.drawable.marker_red);
	    eventOverlay = new EventOverlay(defaultMarker, mapView);
	    mapView.getOverlays().add(eventOverlay);
	    
	    MapController mapController = mapView.getController();
	    mapController.animateTo(new GeoPoint(32802955, -96769923));
	    mapController.setZoom(12);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Bundle extras = getIntent().getExtras(); 
		if(extras != null)
		{
			System.out.println("start: " + String.valueOf(extras.getLong("start")));
			System.out.println("end: " + String.valueOf(extras.getLong("end")));
			System.out.println("distance: " + String.valueOf(extras.getDouble("distance")));
			
			System.out.println(extras.getDouble("distance"));
			EventClient eventClient = new EventClient();
			List<Event> events = eventClient.fetchEvents(
				new Date(extras.getLong("start")), 
				new Date(extras.getLong("end")), 
				Double.valueOf(-96.769923), 
				Double.valueOf(32.802955), 
				Double.valueOf(extras.getDouble("distance"))
			);
			
			for(Event event : events) {
				GeoPoint geoPoint = new GeoPoint((int)(event.getLatitude() * 1E6), (int)(event.getLongitude() * 1E6));  
				OverlayItem item = new OverlayItem(geoPoint, event.getTitle(), event.getId().toString());
				eventOverlay.addItem(item);
			}
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
