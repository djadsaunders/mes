package com.djad.mes.webservice;

import com.djad.mes.service.DCService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTests {

    @InjectMocks
    AdminController adminController;

    @Mock
    DCService dcService;

    @Test
    public void testResetData() {
        adminController.resetData();
        verify(dcService).resetData();
    }
}
