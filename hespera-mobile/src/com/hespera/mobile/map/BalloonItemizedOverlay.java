package com.hespera.mobile.map;

import java.lang.reflect.Method; 
import java.util.List;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.hespera.mobile.R;

public abstract class BalloonItemizedOverlay<Item> extends ItemizedOverlay<OverlayItem> {

	protected MapView mapView;
	private BalloonOverlayView balloonView;
	private View clickRegion;
	private int viewOffset;
	final MapController mc;

	
	public BalloonItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(defaultMarker);
		this.mapView = mapView;
		viewOffset = 0;
		mc = mapView.getController();
	}

	public void setBalloonBottomOffset(int pixels) {
		viewOffset = pixels;
	}

	protected boolean onBalloonTap(int index) {
		return false;
	}

	@Override
	protected final boolean onTap(int index) {

		boolean isRecycled;
		final int thisIndex;
		GeoPoint point;

		thisIndex = index;
		point = createItem(index).getPoint();

		if (balloonView == null) {
			balloonView = new BalloonOverlayView(mapView.getContext(), viewOffset);
			clickRegion = (View) balloonView.findViewById(R.id.balloon_inner_layout);
			isRecycled = false;
		} else {
			isRecycled = true;
		}

		balloonView.setVisibility(View.GONE);

		List<Overlay> mapOverlays = mapView.getOverlays();
		if (mapOverlays.size() > 1) {
			hideOtherBalloons(mapOverlays);
		}

		balloonView.setData(createItem(index));

		MapView.LayoutParams params = new MapView.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, point,
				MapView.LayoutParams.BOTTOM_CENTER);
		params.mode = MapView.LayoutParams.MODE_MAP;

		setBalloonTouchListener(thisIndex);

		balloonView.setVisibility(View.VISIBLE);

		if (isRecycled) {
			balloonView.setLayoutParams(params);
		} else {
			mapView.addView(balloonView, params);
		}

		mc.animateTo(point);

		return true;
	}

	private void hideBalloon() {
		if (balloonView != null) {
			balloonView.setVisibility(View.GONE);
		}
	}

	private void hideOtherBalloons(List<Overlay> overlays) {

		for (Overlay overlay : overlays) {
			if (overlay instanceof BalloonItemizedOverlay<?> && overlay != this) {
				((BalloonItemizedOverlay<?>) overlay).hideBalloon();
			}
		}

	}

	private void setBalloonTouchListener(final int thisIndex) {

		try {
			@SuppressWarnings("unused")
			Method m = this.getClass().getDeclaredMethod("onBalloonTap", int.class);

			clickRegion.setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View v, MotionEvent event) {

					View l =  ((View) v.getParent()).findViewById(R.id.balloon_main_layout);
					Drawable d = l.getBackground();

					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						int[] states = {android.R.attr.state_pressed};
						if (d.setState(states)) {
							d.invalidateSelf();
						}
						return true;
					} else if (event.getAction() == MotionEvent.ACTION_UP) {
						int newStates[] = {};
						if (d.setState(newStates)) {
							d.invalidateSelf();
						}
						// call overridden method
						onBalloonTap(thisIndex);
						return true;
					} else {
						return false;
					}

				}
			});

		} catch (SecurityException e) {
			Log.e("BalloonItemizedOverlay", "setBalloonTouchListener reflection SecurityException");
			return;
		} catch (NoSuchMethodException e) {
			// method not overridden - do nothing
			return;
		}

	}

}

