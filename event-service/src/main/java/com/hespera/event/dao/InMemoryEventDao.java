package com.hespera.event.dao;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hespera.event.model.Event;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
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
    public List<Event> fetch(DateTime begin, DateTime end, Long longitude, Long latitude) {
        return Lists.newArrayList(Iterables.filter(events, Predicates.and(during(begin, end), within(longitude, latitude, 10L))));
    }

    private static Predicate<Event> during(final DateTime begin, final DateTime end) {
        return new Predicate<Event>() {
            @Override
            public boolean apply(@Nullable Event event) {
                return event.getBegin().isBefore(end) || event.getEnd().isAfter(begin);
            }
        };
    }

    private static Predicate<Event> within(final Long longitude, final Long latitude, final Long radius) {
        return new Predicate<Event>() {
            @Override
            public boolean apply(@Nullable Event event) {
                if(event.getLongitude() > longitude + radius) {
                    return false;
                }
                if(event.getLongitude() < longitude - radius) {
                    return false;
                }
                if(event.getLatitude() > latitude + radius) {
                    return false;
                }
                if(event.getLatitude() < latitude - radius) {
                    return false;
                }
                return true;
            }
        };
    }
}
