package com.hespera.event.dao;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hespera.event.model.Event;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.awt.geom.Area;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class InMemoryEventDao implements EventDao {

    private Set<Event> events = Sets.newHashSet();

    @Override
    public void save(Event event) {
        events.add(event);
    }

    @Override
    public Event get(UUID id) {
        for(Event event : events) {
            if(event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public List<Event> fetch(DateTime begin, DateTime end, Area area) {
        return Lists.newArrayList(Iterables.filter(events, Predicates.and(during(begin, end), within(area))));
    }

    private static Predicate<Event> during(final DateTime start, final DateTime end) {
        return new Predicate<Event>() {
            @Override
            public boolean apply(@Nullable Event event) {
                return event.getStart().isBefore(end) && event.getEnd().isAfter(start);
            }
        };
    }

    private static Predicate<Event> within(final Area area) {
        return new Predicate<Event>() {
            @Override
            public boolean apply(@Nullable Event event) {
                return area.contains(event.getLongitude(), event.getLatitude());
            }
        };
    }
}
