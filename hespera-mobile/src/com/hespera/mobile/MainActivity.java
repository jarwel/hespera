package com.hespera.mobile;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
import android.view.View;
import android.widget.TextView;

public class MainActivity extends MapActivity {

	private int time = 3;
	private EventOverlay eventOverlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    InteractiveMapView mapView = (InteractiveMapView)findViewById(R.id.mapview);
	    mapView = (InteractiveMapView)findViewById(R.id.mapview);
	    mapView.setOnChangeListener(new OnChangeListener() {
			public void onChange(MapView view, GeoPoint newCenter, GeoPoint oldCenter, int newZoom, int oldZoom) {
	    		if((!newCenter.equals(oldCenter)) || (newZoom != oldZoom)) {
	    			Log.i(getClass().getSimpleName(), "map view changed");
	    			updateMapView();
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

	public void increaseTime(View view) {
		
		if(time < 24) {
			Log.i(getClass().getSimpleName(), "time increased to next " + ++time + " hours");
			updateMapView();
		}
	}
	
	public void decreaseTime(View view) {
		if(time > 1) {
			Log.i(getClass().getSimpleName(), "time decreased to next " + --time + " hours");
			updateMapView();
		}
	}	
	
	private void updateMapView() {
		MapView mapView = (MapView)findViewById(R.id.mapview);
		
		runOnUiThread(new Runnable() {
			public void run() {
				TextView textView = ((TextView)findViewById(R.id.time_label));
				textView.setText(String.format("Next %s Hours", time));
			}
		});
		
		Calendar start = new GregorianCalendar(); 
		Calendar end = new GregorianCalendar();
		end.add(Calendar.HOUR, time);
		
		long l = mapView.getLatitudeSpan();
		long w = mapView.getLongitudeSpan();
		long y = mapView.getMapCenter().getLatitudeE6() - (l / 2);
		long x = mapView.getMapCenter().getLongitudeE6() - (w / 2);
		
		List<Event> events = EventClient.INSTANCE.fetchEvents(start.getTime(), end.getTime(), (x / 1E6), (y/ 1E6), (w/ 1E6), (l/ 1E6));
		eventOverlay.update(events);
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	

}
