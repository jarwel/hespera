package com.hespera.mobile.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Event {
	
	private final UUID id;
	private final String title;
	private final Date start;
	private final Date end;
	private final Double longitude;
	private final Double latitude;
	private final List<String> tags;
	
	public Event(UUID id, String title, Date start, Date end, Double longitude, Double latitude, List<String> tags) {
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.longitude = longitude;
		this.latitude = latitude;
		this.tags = tags;
	}
	
	public Event(JSONObject jsonObject) throws ParseException, JSONException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		this.id = UUID.fromString(jsonObject.getString("id"));
		this.title = jsonObject.getString("title");
		this.start = sdf.parse(jsonObject.getString("start"));
		this.end = sdf.parse(jsonObject.getString("end"));
		this.longitude = jsonObject.getDouble("longitude");
		this.latitude = jsonObject.getDouble("latitude");
		this.tags = new ArrayList<String>();
		JSONArray jsonArray = jsonObject.optJSONArray("tags");
		for(int i = 0; i < jsonArray.length(); i++) {
			this.tags.add(jsonArray.optString(i));
		}
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public List<String> getTags() {
		return tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
