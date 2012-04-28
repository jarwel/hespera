package com.hespera.extraction;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;

import com.hespera.extraction.model.Event;

public class Loader {
	
	public static void loadAll(List<Event> events) {
		for(Event event: events) {
			load(event);
		}
	}
	
	public static void load(Event event) {
		HttpURLConnection connection = null;
		OutputStreamWriter writer = null;
		
		try {
			URI uri = new URI("http", null, "192.168.1.68", 8001, "/v1/event", null, null);
			
			connection = (HttpURLConnection)uri.toURL().openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(event.toJson());
			writer.flush();
			
			connection.getResponseCode();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				connection.disconnect();
			}
		}
		
	}

}
