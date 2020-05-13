package com.djad.mes.service;

import static org.mockito.Mockito.verify;

import com.djad.mes.repository.AdminRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTests {

    @Mock
    AdminRepository adminRepository;

    @InjectMocks
    AdminService service;

    @Test
    public void testDeleteTransactionalData() {
        service.deleteTransactionalData();
        verify(adminRepository).deleteTransactionalData();
    }
}
