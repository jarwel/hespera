package com.hespera.event.model;

import com.google.common.base.Preconditions;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

import java.util.UUID;

public class Event {

    private final UUID id;
    private final String title;
    private final DateTime begin;
    private final DateTime end;
    private final Double longitude;
    private final Double latitude;

    @JsonCreator
    public Event(
        @JsonProperty("id") UUID id,
        @JsonProperty("title") String title,
        @JsonProperty("begin") DateTime begin,
        @JsonProperty("end") DateTime end,
        @JsonProperty("longitude") Double longitude,
        @JsonProperty("latitude") Double latitude
    ) {
        this.id = Preconditions.checkNotNull(id, "id cannot be null");
        this.title = Preconditions.checkNotNull(title, "title cannot be null");
        this.begin = Preconditions.checkNotNull(begin, "begin cannot be null");
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

    @JsonProperty("begin")
    public DateTime getBegin() {
        return begin;
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
