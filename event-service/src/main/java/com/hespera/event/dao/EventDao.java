package com.hespera.event.dao;

import com.hespera.event.model.Event;

import org.joda.time.DateTime;

import java.util.List;

public interface EventDao {
    List<Event> fetch(DateTime start, DateTime end, Long longitude, Long latitude);
}
