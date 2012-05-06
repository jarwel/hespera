package com.hespera.mobile.event;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import com.hespera.mobile.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event>{
	
	public EventAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy h:mm a");
		sdf.setTimeZone(TimeZone.getDefault());
		
		View view = convertView;
		if(view == null) {
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_item, null);
		}
		
		
		Event event = getItem(position);
		if(event != null) {
			((TextView)view.findViewById(R.id.list_item_title)).setText(event.getTitle());
			((TextView)view.findViewById(R.id.list_item_time)).setText(sdf.format(event.getStart()));
			((TextView)view.findViewById(R.id.list_item_tags)).setText(event.getTags().toString());
		}
		
		return view;
	}
	
	public void update(List<Event> events) {
		Log.i(getClass().getSimpleName(), "refreshing with " + events.size() + " events");
		clear();
		for(Event event : events) {
			add(event);
		}
		notifyDataSetChanged();
	}

}
