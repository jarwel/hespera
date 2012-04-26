package com.hespera.mobile;

import java.util.Calendar;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.hespera.mobile.R;
import com.hespera.mobile.event.Event;
import com.hespera.mobile.event.EventClient;
import com.hespera.mobile.event.EventOverlay;
import com.hespera.mobile.map.InteractiveMapView;
import com.hespera.mobile.map.InteractiveMapView.OnChangeListener;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends MapActivity implements OnSeekBarChangeListener {

	private int time = 3;
	private EventOverlay eventOverlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    SeekBar seekBar = (SeekBar)findViewById(R.id.time_bar);
	    seekBar.setOnSeekBarChangeListener(this);
	    seekBar.setMax(24);
	    seekBar.setProgress(time - 1);
	    
	    InteractiveMapView mapView = (InteractiveMapView)findViewById(R.id.mapview);
	    mapView = (InteractiveMapView)findViewById(R.id.mapview);
	    mapView.setOnChangeListener(new OnChangeListener() {
			public void onChange(MapView view, GeoPoint newCenter, GeoPoint oldCenter, int newZoom, int oldZoom) {
	    		if((!newCenter.equals(oldCenter)) || (newZoom != oldZoom)) {
	    			Log.i(getClass().getSimpleName(), "map view changed");
	    			updateEvents();
	    		}
			}   
	    });
	    mapView.setBuiltInZoomControls(true);
		
	    Drawable defaultMarker = getResources().getDrawable(R.drawable.marker_red);
	    eventOverlay = new EventOverlay(defaultMarker, mapView);
	    mapView.getOverlays().add(eventOverlay);
	    
	    MapController mapController = mapView.getController();
	    mapController.animateTo(new GeoPoint(32802955, -96769923));
	    mapController.setZoom(10);
	}
	
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		time = progress + 1;
		TextView textView = ((TextView)findViewById(R.id.time_label));
		textView.setText(String.format("Events for the next %s hours", time));
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		updateEvents();	
	}
	
	private void updateEvents() {
		MapView mapView = (MapView)findViewById(R.id.mapview);
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
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
