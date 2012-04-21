package com.hespera.event.model;

import com.google.common.base.Preconditions;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.util.UUID;

public class Event {

    private final UUID id;
    private final String title;
    private final DateTime start;
    private final DateTime end;
    private final Long longitude;
    private final Long latitude;

    @JsonCreator
    public Event(
        @JsonProperty("id") UUID id,
        @JsonProperty("title") String title,
        @JsonProperty("start") DateTime start,
        @JsonProperty("end") DateTime end,
        @JsonProperty("longitude") Long longitude,
        @JsonProperty("latitude") Long latitude
    ) {
        this.id = Preconditions.checkNotNull(id, "id cannot be null");
        this.title = Preconditions.checkNotNull(title, "title cannot be null");
        this.start = Preconditions.checkNotNull(start, "start cannot be null");
        this.end = Preconditions.checkNotNull(end, "end cannot be null");
        this.longitude = Preconditions.checkNotNull(longitude, "longitude cannot be null");
        this.latitude = Preconditions.checkNotNull(latitude, "latitude cannot be null");
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
    public Long getLongitude() {
        return longitude;
    }

    @JsonProperty("latitude")
    public Long getLatitude() {
        return latitude;
    }
}
