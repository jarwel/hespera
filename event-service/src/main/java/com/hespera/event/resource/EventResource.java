package com.hespera.event.resource;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.hespera.event.dao.EventDao;
import com.hespera.event.model.Event;
import com.proofpoint.log.Logger;
import org.joda.time.DateTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.UUID;

@Path("v1/event")
public class EventResource {
    private final static Logger LOGGER = Logger.get(EventResource.class);

    private final EventDao eventDao;

    @Inject
    public EventResource(EventDao eventDao) {
        this.eventDao = Preconditions.checkNotNull(eventDao, "eventDao cannot be null");
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") UUID id) {
        Event event = eventDao.get(id);
        if(event == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(event).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(
        @QueryParam("start") Long start,
        @QueryParam("end") Long end,
        @QueryParam("x") Double x,
        @QueryParam("y") Double y,
        @QueryParam("w") Double w,
        @QueryParam("l") Double l
    ) {
        Area area = new Area(new Rectangle2D.Double(x, y, w, l));
        List<Event> events = eventDao.fetch(new DateTime(start), new DateTime(end), area);

        LOGGER.info("search returning " + events.size() + " events");
        return Response.ok(events).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Event event) {
        eventDao.save(event);
        return Response.ok().build();
    }
}
