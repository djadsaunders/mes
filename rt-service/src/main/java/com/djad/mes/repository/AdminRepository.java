package com.djad.mes.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdminRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void deleteTransactionalData() {
        //TODO: should be transactional
        jdbcTemplate.execute("DELETE FROM availability_period");
        jdbcTemplate.execute("DELETE FROM crew_period");
        jdbcTemplate.execute("DELETE FROM shift_period");
        jdbcTemplate.execute("DELETE FROM production_state_period");
        jdbcTemplate.execute("DELETE FROM production_run_period");
    }
}