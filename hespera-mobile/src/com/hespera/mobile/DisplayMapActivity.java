package com.hespera.mobile;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.hespera.mobile.R;

import android.os.Bundle;

public class DisplayMapActivity extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.display_map);
	    
	    MapView mapView = (MapView)findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);

	    MapController mapController = mapView.getController();
	    mapController.animateTo(new GeoPoint(32802955, -96769923));
	    mapController.setZoom(12);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
