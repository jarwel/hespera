package com.hespera.event.dao;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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


}
