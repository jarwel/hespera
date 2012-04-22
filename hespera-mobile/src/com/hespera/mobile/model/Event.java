package com.hespera.mobile.model;

import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;

public class Event {
	
	private final UUID id;
	private final String title;
	private final Date start;
	private final Date end;
	private final Double longitude;
	private final Double latitude;
	
	public Event(UUID id, String title, Date start, Date end, Double longitude, Double latitude) {
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Event(JSONObject jsonObject) {
		this.id = UUID.fromString(jsonObject.optString("id"));
		this.title = jsonObject.optString("title");
		this.start = new Date(jsonObject.optLong("start"));
		this.end = new Date(jsonObject.optLong("end"));
		this.longitude = jsonObject.optDouble("longitude");
		this.latitude = jsonObject.optDouble("latitude");
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
