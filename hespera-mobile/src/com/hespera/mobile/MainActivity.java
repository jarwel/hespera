package com.hespera.mobile;



import java.util.Calendar;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.hespera.mobile.R;
import com.hespera.mobile.dao.EventDao;
import com.hespera.mobile.event.Event;
import com.hespera.mobile.event.EventAdapter;
import com.hespera.mobile.event.EventOverlay;
import com.hespera.mobile.map.InteractiveMapView;
import com.hespera.mobile.map.InteractiveMapView.OnChangeListener;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ViewAnimator;

public class MainActivity extends MapActivity implements OnSeekBarChangeListener {

	private int time = 3;
	private MapController mapController;
	private MyLocationOverlay locationOverlay;
	private EventOverlay eventOverlay;
	private EventAdapter eventAdapter;
	
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
	    
	    // All update tasks should be initiated from the UI thread as AsyncTask to control execution
	    //TODO: Re-factor for clarity
	    mapView.setOnChangeListener(new OnChangeListener() {
			public void onChange(MapView view, GeoPoint newCenter, GeoPoint oldCenter, int newZoom, int oldZoom) {
	    		if((!newCenter.equals(oldCenter)) || (newZoom != oldZoom)) {
	    			Log.i(getClass().getSimpleName(), "map view changed");
	    			runOnUiThread(new Runnable() {
						public void run() {
							new UpdateEventsTask().execute();
						}
					});
	    		}
			}   
	    });
	    mapView.setBuiltInZoomControls(true);
		
	    Drawable defaultMarker = getResources().getDrawable(R.drawable.marker_red);
	    eventOverlay = new EventOverlay(defaultMarker, mapView);
	    mapView.getOverlays().add(eventOverlay);
	    
	    mapController = mapView.getController();
	    
	    LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
	    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    
	    locationOverlay = new MyLocationOverlay(this, mapView);
	    locationOverlay.enableMyLocation();
	    mapView.getOverlays().add(locationOverlay);
	    mapController.animateTo(new GeoPoint((int)(location.getLatitude() * 1E6), (int)(location.getLongitude() * 1E6)));
	    mapController.setZoom(14);
	    
	    eventAdapter = new EventAdapter(this, R.layout.list_item);
	    ListView listView = (ListView)findViewById(R.id.listview);
	    listView.setAdapter(eventAdapter);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		locationOverlay.enableMyLocation();
		locationOverlay.runOnFirstFix(new Runnable() {
			public void run() {
				mapController.animateTo(locationOverlay.getMyLocation());
			}
		});
	}
	
	@Override
	public void onPause() {
		super.onPause();
		locationOverlay.disableMyLocation();
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
		new UpdateEventsTask().execute();
	}
	
	public void showMap(View v) {
		((ViewAnimator)findViewById(R.id.viewanimator)).setDisplayedChild(0);
	}
	
	public void showList(View v) {
		((ViewAnimator)findViewById(R.id.viewanimator)).setDisplayedChild(1);
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	private class UpdateEventsTask extends AsyncTask<Void, Void, List<Event>> {
		
		private final Calendar start, end;
		private final long l, w, y, x;
		
		public UpdateEventsTask() {
			start = Calendar.getInstance();
			end = Calendar.getInstance();
			end.add(Calendar.HOUR, time);
			
			MapView mapView = (MapView)findViewById(R.id.mapview);
			l = mapView.getLatitudeSpan();
			w = mapView.getLongitudeSpan();
			y = mapView.getMapCenter().getLatitudeE6() - (l / 2);
			x = mapView.getMapCenter().getLongitudeE6() - (w / 2);
		}

		@Override
		protected List<Event> doInBackground(Void... params) {
			return EventDao.INSTANCE.fetchEvents(start.getTime(), end.getTime(), (x / 1E6), (y / 1E6), (w / 1E6), (l / 1E6));
		}
		
		@Override
		protected void onPostExecute(List<Event> events) {
			eventOverlay.update(events);
			eventAdapter.update(events);
		}

	}
	
}
