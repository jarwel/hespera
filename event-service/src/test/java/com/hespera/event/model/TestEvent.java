package com.hespera.event.model;

import com.google.common.collect.Lists;
import com.proofpoint.json.JsonCodec;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.UUID;

import static com.proofpoint.testing.EquivalenceTester.equivalenceTester;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestEvent {

    private static JsonCodec<Event> eventCodec = JsonCodec.jsonCodec(Event.class);

    @Test
    public void testRoundTrip() {
        Event expected = new Event(UUID.randomUUID(), "An Event", DateTime.now(), DateTime.now(), -122.3933494, 37.7777683, Lists.newArrayList("sports"));
        Event actual = eventCodec.fromJson(eventCodec.toJson(expected));

        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getTitle(), expected.getTitle());
        assertTrue(actual.getStart().isEqual(expected.getStart()));
        assertTrue(actual.getEnd().isEqual(expected.getEnd()));
        assertEquals(actual.getLongitude(), expected.getLongitude());
        assertEquals(actual.getLatitude(), expected.getLatitude());
        assertEquals(actual.getTags(), expected.getTags());
    }

    @Test
    public void testEquivalence() {
        equivalenceTester()
            .addEquivalentGroup(
                new Event(UUID.fromString("0de8861b-9d80-4396-8d45-1e681b6f69a8"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), 122.3933494, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("0de8861b-9d80-4396-8d45-1e681b6f69a8"), "Event 2", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), -122.3933494, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("0de8861b-9d80-4396-8d45-1e681b6f69a8"), "Event 1", new DateTime(Integer.MAX_VALUE), new DateTime(Integer.MAX_VALUE), -122.3933494, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("0de8861b-9d80-4396-8d45-1e681b6f69a8"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MIN_VALUE), -122.3933494, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("0de8861b-9d80-4396-8d45-1e681b6f69a8"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), 37.7777683, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("0de8861b-9d80-4396-8d45-1e681b6f69a8"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), -122.3933494, -122.3933494, Lists.newArrayList("sports")),
                new Event(UUID.fromString("0de8861b-9d80-4396-8d45-1e681b6f69a8"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), 122.3933494, 37.7777683, Lists.newArrayList("music"))
            )
            .addEquivalentGroup(
                new Event(UUID.fromString("d4e7a756-5034-4815-b0f3-c338d802b5d6"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), -122.3933494, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("d4e7a756-5034-4815-b0f3-c338d802b5d6"), "Event 2", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), -122.3933494, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("d4e7a756-5034-4815-b0f3-c338d802b5d6"), "Event 1", new DateTime(Integer.MAX_VALUE), new DateTime(Integer.MAX_VALUE), -122.3933494, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("d4e7a756-5034-4815-b0f3-c338d802b5d6"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MIN_VALUE), -122.3933494, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("d4e7a756-5034-4815-b0f3-c338d802b5d6"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), 37.7777683, 37.7777683, Lists.newArrayList("sports")),
                new Event(UUID.fromString("d4e7a756-5034-4815-b0f3-c338d802b5d6"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), -122.3933494, -122.3933494, Lists.newArrayList("sports")),
                new Event(UUID.fromString("d4e7a756-5034-4815-b0f3-c338d802b5d6"), "Event 1", new DateTime(Integer.MIN_VALUE), new DateTime(Integer.MAX_VALUE), -122.3933494, 37.7777683, Lists.newArrayList("music"))
            )
        .check();
    }

}
