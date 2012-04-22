package com.hespera.event.dao;

import com.hespera.event.model.Event;

import org.joda.time.DateTime;

import java.awt.geom.Area;
import java.util.List;
import java.util.UUID;

public interface EventDao {
    void save(Event event);
    Event get(UUID id);
    List<Event> fetch(DateTime begin, DateTime end, Area area);
}
