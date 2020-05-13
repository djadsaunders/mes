package com.djad.mes.domain;

import com.djad.mes.domain.resource.Resource;
import com.djad.mes.domain.resource.ResourceFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ResourceFactoryTests {

    @InjectMocks
    ResourceFactory resourceFactory;

    @Test
    public void testCreateResourceWithTagAndName() {
        String tag = "LINEA";
        String name = "Line A";

        Resource resource = resourceFactory.createResource(tag, name);
        assertEquals(resource.getTag(), tag);
        assertEquals(resource.getName(), name);
        assertTrue(resource.isStopped());
        assertFalse(resource.isAvailable());
    }

    @Test
    public void testCreateResourceWithTag() {
        String tag = "LINEA";

        Resource resource = resourceFactory.createResource(tag);
        assertEquals(resource.getTag(), tag);
        assertEquals(resource.getName(), tag);
        assertTrue(resource.isStopped());
        assertFalse(resource.isAvailable());
    }
}
