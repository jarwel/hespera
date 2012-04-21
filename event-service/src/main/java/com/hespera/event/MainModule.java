package com.hespera.event;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.hespera.event.dao.EventDao;
import com.hespera.event.dao.InMemoryEventDao;
import com.hespera.event.resource.EventResource;
import com.proofpoint.discovery.client.DiscoveryBinder;

public class MainModule implements Module {
    private static final String SERVICE_NAME = "event";

    @Override
    public void configure(Binder binder) {
        binder.requireExplicitBindings();
        binder.disableCircularProxies();

        binder.bind(EventDao.class).to(InMemoryEventDao.class).in(Scopes.SINGLETON);
        binder.bind(EventResource.class).in(Scopes.SINGLETON);

        DiscoveryBinder.discoveryBinder(binder).bindHttpAnnouncement(SERVICE_NAME);
    }
}
