package com.hespera.scraper.model;

import java.util.Date;
import java.util.UUID;

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
	
	public String toJson() {
		return new StringBuilder("{")
			.append("\"id\":\"" + id.toString() + "\",")
			.append("\"title\":\"" + title + "\",")
			.append("\"start\":" + start.getTime() + ",")
			.append("\"end\":" + end.getTime() + ",")
			.append("\"longitude\":" + longitude + ",")
			.append("\"latitude\":" + latitude + "}")
		.toString();
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

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", start=" + start
				+ ", end=" + end + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}

}
