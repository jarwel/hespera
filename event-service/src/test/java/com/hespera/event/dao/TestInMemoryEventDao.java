package com.hespera.event.dao;

import com.google.common.collect.Lists;
import com.hespera.event.model.Event;
import org.joda.time.DateTime;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestInMemoryEventDao {

    private EventDao eventDao;

    @BeforeMethod
    public void setUp() {
        this.eventDao = new InMemoryEventDao();
    }

    @AfterMethod
    public void tearDown() {
        this.eventDao = null;
    }

    @Test
    public void testSave() {
        Event expected = new Event(UUID.randomUUID(), "An Event", DateTime.now(), DateTime.now(), -122.3933494, 37.7777683, Lists.newArrayList("sports"));
        eventDao.save(expected);

        Event actual = eventDao.get(expected.getId());

        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getTitle(), expected.getTitle());
        assertTrue(actual.getStart().isEqual(expected.getStart()));
        assertTrue(actual.getEnd().isEqual(expected.getEnd()));
        assertEquals(actual.getLongitude(), expected.getLongitude());
        assertEquals(actual.getLatitude(), expected.getLatitude());
        assertEquals(actual.getTags(), expected.getTags());
    }

    @Test
    public void testGet() {
        Event expected = new Event(UUID.randomUUID(), "An Event", DateTime.now(), DateTime.now(), -122.3933494, 37.7777683, Lists.newArrayList("sports"));
        eventDao.save(expected);

        Event actual = eventDao.get(expected.getId());

        assertEquals(actual, expected);
    }
}
