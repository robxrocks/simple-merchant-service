package com.robxrocks.simple.merchant.service.db;

import com.robxrocks.simple.merchant.service.entity.Offer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class MemoryDatastoreImplTest {

    public MemoryDatastoreImpl specUnderTest;

    @Before
    public void setUp() {
        specUnderTest = new MemoryDatastoreImpl();
    }

    @Test
    public void testGetDatastore() throws Exception {
        assertNotNull(specUnderTest.getDatastore());
    }

    @Test
    public void testAddEntity() {
        Offer entity = mock(Offer.class);
        specUnderTest.addEntity(entity);
        assertThat(specUnderTest.getDatastore().get(0), is(entity));
    }

}