package com.hespera.mobile.map; 

import java.util.Timer;
import java.util.TimerTask; 
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent; 
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView; 

public class InteractiveMapView extends MapView {    
	
	public interface OnChangeListener {
		public void onChange(MapView view, GeoPoint newCenter, GeoPoint oldCenter, int newZoom, int oldZoom);
	}

	private InteractiveMapView mThis;
	private long mEventsTimeout = 250L;
	private boolean mIsTouched = false;
	private GeoPoint mLastCenterPosition;
	private int mLastZoomLevel;
	private Timer mChangeDelayTimer = new Timer();
	private InteractiveMapView.OnChangeListener mChangeListener = null;

	public InteractiveMapView(Context context, String apiKey) {
		super(context, apiKey);
		init();
	}    
	
	public InteractiveMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public InteractiveMapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		mThis = this;
		mLastCenterPosition = this.getMapCenter();
		mLastZoomLevel = this.getZoomLevel();
	}
	
	public void setOnChangeListener(InteractiveMapView.OnChangeListener l) {
		mChangeListener = l;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		mIsTouched = (ev.getAction() != MotionEvent.ACTION_UP);
		return super.onTouchEvent(ev);
	}
	
	@Override
	public void computeScroll() {
		super.computeScroll();
		if(isSpanChange() || isZoomChange()){
			resetMapChangeTimer();
		}
	}

	private void resetMapChangeTimer() { 
		mChangeDelayTimer.cancel();
		mChangeDelayTimer = new Timer();
		mChangeDelayTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(mChangeListener != null) {
					mChangeListener.onChange(mThis, getMapCenter(), mLastCenterPosition, getZoomLevel(), mLastZoomLevel);
				}	
				mLastCenterPosition = getMapCenter();
				mLastZoomLevel = getZoomLevel();
			}
		}, mEventsTimeout);
	}

	private boolean isSpanChange() {        
		return !mIsTouched && !getMapCenter().equals(mLastCenterPosition);
	} 
	
	private boolean isZoomChange() {
		return (getZoomLevel() != mLastZoomLevel);
	}
	
}
	
