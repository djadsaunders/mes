package com.djad.mes.service;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;

import com.djad.mes.events.Event;
import com.djad.mes.events.EventSender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.djad.mes.events.EventSender.Topic;

@RunWith(MockitoJUnitRunner.class)
public class DCServiceTests {

    private final String DEFAULT_LINE_TAG = "LINEA";

    // EventSender eventSender = mock(EventSender.class);

    @Mock
    EventSender eventSender;

    //@Mock
    //ApplicationContextDelegate applicationContextDelegate;

    @InjectMocks
    DCService dcService;

    //@Before
    //public void setup() {
    //    when(applicationContextDelegate.getBean(any())).thenReturn(eventSender);
    //}

    @Test
    public void testSetRunningFullSpeed() {
        dcService.setRunningFullSpeed(DEFAULT_LINE_TAG);
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.PRODUCTION_STATE, "2")), refEq(Topic.MES));
    }

    @Test
    public void testSetRunningSlow() {
        dcService.setRunningSlow(DEFAULT_LINE_TAG);
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.PRODUCTION_STATE, "1")), refEq(Topic.MES));
    }

    @Test
    public void testStop() {
        dcService.stop(DEFAULT_LINE_TAG);
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.PRODUCTION_STATE, "0")), refEq(Topic.MES));
    }

    @Test
    public void testMakeAvailable() {
        dcService.makeAvailable(DEFAULT_LINE_TAG);
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.AVAILABILITY, "1")), refEq(Topic.MES));
    }

    @Test
    public void testMakeUnavailable() {
        dcService.makeUnavailable(DEFAULT_LINE_TAG);
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.AVAILABILITY, "0")), refEq(Topic.MES));
    }

    @Test
    public void testChangeShift() {
        dcService.changeShift(DEFAULT_LINE_TAG, "AM");
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.SHIFT, "AM")), refEq(Topic.MES));
    }

    @Test
    public void testChangeCrew() {
        dcService.changeCrew(DEFAULT_LINE_TAG, "BLUE");
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.CREW, "BLUE")), refEq(Topic.MES));
    }

    @Test
    public void testChangeRun() {
        dcService.changeRun(DEFAULT_LINE_TAG, "001");
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.PRODUCTION_RUN, "001")), refEq(Topic.MES));
    }

    @Test
    public void testLogInCount() {
        dcService.logInCount(DEFAULT_LINE_TAG, 10.0);
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.COUNTER_IN, "10.0")), refEq(Topic.MES));
    }

    @Test
    public void testLogOutCount() {
        dcService.logOutCount(DEFAULT_LINE_TAG, 10.0);
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.COUNTER_OUT, "10.0")), refEq(Topic.MES));
    }

    @Test
    public void testCreateResource() {
        dcService.createResource(DEFAULT_LINE_TAG, DEFAULT_LINE_TAG);
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.CREATE_RESOURCE,
                        DEFAULT_LINE_TAG + "," + DEFAULT_LINE_TAG)), refEq(Topic.MES));
    }

    @Test
    public void testRemoveResource() {
        dcService.removeResource(DEFAULT_LINE_TAG);
        verify(eventSender).sendEvent(refEq(
                new Event(DEFAULT_LINE_TAG, Event.TransactionTypes.REMOVE_RESOURCE, DEFAULT_LINE_TAG)), refEq(Topic.MES));
    }

    @Test
    public void testResetData() {
        dcService.resetData();
        verify(eventSender).sendEvent(refEq(
                new Event(Event.TransactionTypes.RESET_DATA)), refEq(Topic.MES));
    }
}
