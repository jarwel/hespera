package com.hespera.mobile;

import java.util.Date;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.hespera.mobile.R;
import com.hespera.mobile.client.EventClient;
import com.hespera.mobile.map.EventOverlay;
import com.hespera.mobile.map.InteractiveMapView;
import com.hespera.mobile.map.InteractiveMapView.OnChangeListener;
import com.hespera.mobile.model.Event;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

public class DisplayMapActivity extends MapActivity {

	private InteractiveMapView mapView;
	private EventOverlay eventOverlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.display_map);
	    
	    mapView = (InteractiveMapView)findViewById(R.id.mapview);
	    mapView.setOnChangeListener(new OnChangeListener() {
			public void onChange(MapView view, GeoPoint newCenter, GeoPoint oldCenter, int newZoom, int oldZoom) {
	    		if((!newCenter.equals(oldCenter)) || (newZoom != oldZoom)) {
	    			Log.i(getClass().getSimpleName(), "map view changed");
	    			updateMap();
	    		}
			}   
	    });
	    mapView.setBuiltInZoomControls(true);
		
	    Drawable defaultMarker = getResources().getDrawable(R.drawable.marker_red);
	    eventOverlay = new EventOverlay(defaultMarker, mapView);
	    mapView.getOverlays().add(eventOverlay);
	    
	    MapController mapController = mapView.getController();
	    mapController.animateTo(new GeoPoint(32802955, -96769923));
	    mapController.setZoom(12);
	}
	
	private void updateMap() {
		EventClient eventClient = new EventClient();
		
		long l = mapView.getLatitudeSpan();
		long w = mapView.getLongitudeSpan();
		long y = mapView.getMapCenter().getLatitudeE6() - (l / 2);
		long x = mapView.getMapCenter().getLongitudeE6() - (w / 2);
		
		List<Event> events = eventClient.fetchEvents(new Date(0, 1, 1), new Date(2020, 1, 1), (x / 1E6), (y/ 1E6), (w/ 1E6), (l/ 1E6));
		eventOverlay.update(events);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
