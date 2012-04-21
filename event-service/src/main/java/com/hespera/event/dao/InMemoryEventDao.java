package com.hespera.event.dao;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hespera.event.model.Event;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class InMemoryEventDao implements EventDao {

    private Set<Event> events = Sets.newHashSet();

    @Override
    public List<Event> fetch(DateTime start, DateTime end, Long longitude, Long latitude) {
        return Lists.newArrayList(Iterables.filter(events, intersects(longitude, latitude, 10L)));
    }

    private static Predicate<Event> intersects(final Long longitude, final Long latitude, final Long radius) {
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
