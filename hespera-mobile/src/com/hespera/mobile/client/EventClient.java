package com.hespera.mobile.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.hespera.mobile.model.Event;

public class EventClient {
	
	public List<Event> fetchEvents(Date start, Date end, Double longitude, Double latitude, Double distance) {
		List<Event> events = new ArrayList<Event>();
		
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		try {
			String params = new StringBuilder()
				.append("start=").append(start.getTime())
				.append("&end=").append(end.getTime())
				.append("&longitude=").append(longitude)
				.append("&latitude=").append(latitude)
				.append("&distance=").append(distance)
			.toString();
			
			URI uri = new URI("http", null, "192.168.1.68", 8001, "/v1/event", params, null);
		
			connection = (HttpURLConnection)uri.toURL().openConnection();
			reader = new  BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String response = reader.readLine();
			if(response != null) {
				JSONArray jsonArray = new JSONArray(response);
				for(int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.optJSONObject(i);
					if(jsonObject != null) {
						events.add(new Event(jsonObject));
					}
				}
			}
		} catch (Exception e) {
			Log.i(getClass().getSimpleName(), "exception fetching events", e);
		}
		finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					Log.i(getClass().getSimpleName(), "exception closing reader", e);
				}
			}
			if(connection != null) {
				connection.disconnect();
			}
		}

		Log.i(getClass().getSimpleName(), "fetched " + String.valueOf(events.size()) + " events");
		return events;
	}

}
