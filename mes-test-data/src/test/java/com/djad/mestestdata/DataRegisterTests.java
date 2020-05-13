package com.djad.mestestdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;

@RunWith(MockitoJUnitRunner.class)
public class DataRegisterTests {
    
    DataRegister dataRegister;

    @Before
    public void setup() {
        dataRegister = DataRegister.getInstance();
    }

    @After
    public void tearDown() {
        dataRegister.clearRegister();
    }
    
    @Test
    public void testAddItems() {
        dataRegister.addItems(new DataItem("type1", "val1"),
            new DataItem("type2", "val2"),
            new DataItem("type3", "val3")
            );
        assertEquals(3, dataRegister.getDataStream().count());
    }

    @Test
    public void testAddItems2() {
        dataRegister.addItems("type", "val1", "val2", "val3");
        assertEquals(3, dataRegister.getDataStream().count());
    }

    @Test
    public void testGetAllValuesByType() {
        dataRegister.addItems(new DataItem("type1", "val1"),
            new DataItem("type2", "val2"),
            new DataItem("type2", "val3")
            );
        assertEquals(2, dataRegister.getAllValuesByType("type2").size());
    }
}
