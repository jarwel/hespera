package com.hespera.extraction.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Event {
	
	private final UUID id;
	private final String title;
	private final Date start;
	private final Date end;
	private final Double longitude;
	private final Double latitude;
	private final List<String> tags;
	
	@JsonCreator
	public Event(
		@JsonProperty("id") UUID id, 
		@JsonProperty("title") String title, 
		@JsonProperty("start") Date start, 
		@JsonProperty("end") Date end, 
		@JsonProperty("longitude") Double longitude,
		@JsonProperty("latitude") Double latitude, 
		@JsonProperty("tags") List<String> tags
	) {
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.longitude = longitude;
		this.latitude = latitude;
		this.tags = tags;
	}
	
	@JsonProperty("id")
	public UUID getId() {
		return id;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("start")
	public Date getStart() {
		return start;
	}

	@JsonProperty("end")
	public Date getEnd() {
		return end;
	}

	@JsonProperty("longitude")
	public Double getLongitude() {
		return longitude;
	}

	@JsonProperty("latitude")
	public Double getLatitude() {
		return latitude;
	}

	@JsonProperty("tags")
	public List<String> getTags() {
		return tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", start=" + start
				+ ", end=" + end + ", longitude=" + longitude + ", latitude="
				+ latitude + ", tags=" + tags + "]";
	}

}
