package com.robxrocks.simple.merchant.service.dao;

import com.robxrocks.simple.merchant.service.db.Datastore;
import com.robxrocks.simple.merchant.service.entity.Offer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class OfferDaoImplTest {

    private OfferDaoImpl specUnderTest;
    private Datastore datastore;

    @Before
    public void setUp() {
        datastore = mock(Datastore.class);
        specUnderTest = new OfferDaoImpl(datastore);
    }

    @Test
    public void testGetAll_Success() throws Exception {
        when(datastore.getDatastore()).thenReturn(new ArrayList<>());
        specUnderTest.getAll();
        verify(datastore, times(1)).getDatastore();
    }

    @Test
    public void testSave_Success() throws Exception {
        doNothing().when(datastore).addEntity(anyObject());
        Offer entity = mock(Offer.class);
        specUnderTest.save(entity);
        verify(datastore, times(1)).addEntity(entity);
    }

}