package com.hespera.event.dao;

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
        Event expected = new Event(UUID.randomUUID(), "An Event", DateTime.now(), DateTime.now(), Long.valueOf(0), Long.valueOf(0));
        eventDao.save(expected);

        Event actual = eventDao.get(expected.getId());

        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getTitle(), expected.getTitle());
        assertTrue(actual.getBegin().isEqual(expected.getBegin()));
        assertTrue(actual.getEnd().isEqual(expected.getEnd()));
        assertEquals(actual.getLongitude(), expected.getLongitude());
        assertEquals(actual.getLatitude(), expected.getLatitude());
    }

    @Test
    public void testGet() {
        Event expected = new Event(UUID.randomUUID(), "An Event", DateTime.now(), DateTime.now(), Long.valueOf(0), Long.valueOf(0));
        eventDao.save(expected);

        Event actual = eventDao.get(expected.getId());

        assertEquals(actual, expected);
    }
}
