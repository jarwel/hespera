package com.hespera.event.model;

import com.google.common.base.Preconditions;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

public class Event {

    private final UUID id;
    private final String title;
    private final DateTime start;
    private final DateTime end;
    private final Double longitude;
    private final Double latitude;
    private final List<String> tags;

    @JsonCreator
    public Event(
        @JsonProperty("id") UUID id,
        @JsonProperty("title") String title,
        @JsonProperty("start") DateTime start,
        @JsonProperty("end") DateTime end,
        @JsonProperty("longitude") Double longitude,
        @JsonProperty("latitude") Double latitude,
        @JsonProperty("tags") List<String> tags
    ) {
        this.id = Preconditions.checkNotNull(id, "id cannot be null");
        this.title = Preconditions.checkNotNull(title, "title cannot be null");
        this.start = Preconditions.checkNotNull(start, "start cannot be null");
        this.end = Preconditions.checkNotNull(end, "end cannot be null");
        this.longitude = Preconditions.checkNotNull(longitude, "longitude cannot be null");
        this.latitude = Preconditions.checkNotNull(latitude, "latitude cannot be null");
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
    public DateTime getStart() {
        return start;
    }

    @JsonProperty("end")
    public DateTime getEnd() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!id.equals(event.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
