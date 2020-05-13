package com.djad.mes.domain;

import com.djad.mes.domain.crew.Crew;
import com.djad.mes.domain.product.Product;
import com.djad.mes.domain.product.ProductionRun;
import com.djad.mes.domain.resource.Resource;
import com.djad.mes.domain.shift.Shift;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ResourceTests {

    private Resource testResource;

    @Before
    public void setup() {
        this.testResource = new Resource("A");
    }

    @Test
    public void testSetRunningFullSpeed() {
        testResource.setRunningFullSpeed();
        assertTrue(testResource.isRunningFullSpeed());
        assertFalse(testResource.setRunningFullSpeed());
    }

    @Test
    public void testSetRunningSlow() {
        testResource.setRunningSlow();
        assertTrue(testResource.isRunningSlow());
        assertFalse(testResource.setRunningSlow());
    }

    @Test
    public void testStop() {
        testResource.stop();
        assertTrue(testResource.isStopped());
        assertFalse(testResource.stop());
    }

    @Test
    public void testMakeAvailable() {
        testResource.makeAvailable();
        assertFalse(testResource.makeAvailable());
    }

    @Test
    public void testMakeUnavailable() {
        testResource.makeUnavailable();
        assertFalse(testResource.makeUnavailable());
    }

    @Test
    public void testChangeShift() {
        boolean change1 = testResource.changeShift(new Shift("Old Shift"));
        boolean change2 = testResource.changeShift(new Shift("New Shift"));

        assertTrue(change1 && change2);

        assertEquals(testResource.getPreviousShift().get().getName(), "Old Shift");
        assertEquals(testResource.getCurrentShift().get().getName(), "New Shift");
    }

    @Test
    public void testChangeShiftToSameShift() {
        boolean change1 = testResource.changeShift(new Shift("A"));
        boolean change2 = testResource.changeShift(new Shift("A"));

        assertTrue(change1 && !change2);
    }

    @Test
    public void testChangeCrew() {
        boolean change1 = testResource.changeCrew(new Crew("A"));
        boolean change2 = testResource.changeCrew(new Crew("B"));

        assertTrue(change1 && change2);

        assertEquals(testResource.getPreviousCrew().get().getName(), "A");
        assertEquals(testResource.getCurrentCrew().get().getName(), "B");
    }

    @Test
    public void testChangeCrewToSameCrew() {
        boolean change1 = testResource.changeCrew(new Crew("A"));
        boolean change2 = testResource.changeCrew(new Crew("A"));

        assertTrue(change1 && !change2);
    }

    @Test
    public void testChangeProductionRun() {
        boolean change1 = testResource.changeProductionRun(new ProductionRun(new Product("P","P"), "A"));
        boolean change2 = testResource.changeProductionRun(new ProductionRun(new Product("P","P"), "B"));

        assertTrue(change1 && change2);

        assertEquals(testResource.getPreviousProductionRun().get().getName(), "A");
        assertEquals(testResource.getCurrentProductionRun().get().getName(), "B");
    }

    @Test
    public void testChangeProductionRunToSameRun() {
        boolean change1 = testResource.changeProductionRun(new ProductionRun(new Product("P","P"), "A"));
        boolean change2 = testResource.changeProductionRun(new ProductionRun(new Product("P","P"), "A"));

        assertTrue(change1 && !change2);
    }

    @Test
    public void testLogInCount() {
        testResource.logInCount(10.5);
        testResource.logInCount(12.7);
        assertEquals(Double.valueOf(testResource.getPreviousInCountValue()), Double.valueOf(10.5));
        assertEquals(Double.valueOf(testResource.getCurrentInCountValue()), Double.valueOf(12.7));
    }

    @Test
    public void testLogOutCount() {
        testResource.logOutCount(10.5);
        testResource.logOutCount(12.7);
        assertEquals(Double.valueOf(testResource.getPreviousOutCountValue()), Double.valueOf(10.5));
        assertEquals(Double.valueOf(testResource.getCurrentOutCountValue()), Double.valueOf(12.7));
    }
}
