package com.hespera.mobile.map;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.maps.OverlayItem;
import com.hespera.mobile.R;
import com.hespera.mobile.event.EventOverlayItem;

public class BalloonOverlayView extends FrameLayout {

	private LinearLayout layout;
	private TextView title;
	private TextView time;
	private TextView tags;

	public BalloonOverlayView(Context context, int balloonBottomOffset) {
		super(context);

		setPadding(10, 0, 10, balloonBottomOffset);
		layout = new LinearLayout(context);
		layout.setVisibility(VISIBLE);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.balloon_overlay, layout);
		title = (TextView)v.findViewById(R.id.balloon_item_title);
		time = (TextView)v.findViewById(R.id.balloon_item_time);
		tags = (TextView)v.findViewById(R.id.balloon_item_tags);

		ImageView close = (ImageView) v.findViewById(R.id.close_img_button);
		close.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				layout.setVisibility(GONE);
			}
		});

		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.NO_GRAVITY;

		addView(layout, params);

	}

	public void setData(OverlayItem item) {

		EventOverlayItem eventOverlayItem = (EventOverlayItem)item;
		
		layout.setVisibility(VISIBLE);
		if (eventOverlayItem.getTitle() != null) {
			title.setVisibility(VISIBLE);
			title.setText(eventOverlayItem.getTitle());
		} else {
			title.setVisibility(GONE);
		}
		if (eventOverlayItem.getTime() != null) {
			time.setVisibility(VISIBLE);
			time.setText(eventOverlayItem.getTime());
		} else {
			time.setVisibility(GONE);
		}
		if (eventOverlayItem.getTags() != null) {
			tags.setVisibility(VISIBLE);
			tags.setText(eventOverlayItem.getTags());
		} else {
			tags.setVisibility(GONE);
		}


	}

}

