package com.djad.mes.repository;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.djad.mes.repository.AdminRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class AdminRepositoryTests {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    AdminRepository repository;

    @Test
    public void testDeleteTransactionalData() {
        repository.deleteTransactionalData();
        verify(jdbcTemplate, times(5)).execute(anyString());
    }
}
