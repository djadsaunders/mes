package com.djad.mesquery.repository;

import com.djad.mesquery.dto.ProductionRunDTO;
import com.djad.mesquery.dto.ResourceDTO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class QueryRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ResourceDTO getSingleResource(String tag) {
        String[] params = {tag};
        return jdbcTemplate.queryForObject(SQLQueries.SINGLE_RESOURCE
                ,params
                ,(rs, rownum) -> new ResourceDTO(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8))
        );
    }

    public List<ResourceDTO> getAllResources() {
        return jdbcTemplate.query(SQLQueries.ALL_RESOURCES
                ,(rs, rownum) -> new ResourceDTO(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8))
        );
    }

    public ProductionRunDTO getProductionRun(String tag) {
        String[] params = {tag};
        return jdbcTemplate.queryForObject(SQLQueries.PRODUCTION_RUN_INFO
                ,params
                ,(rs, rownum) -> new ProductionRunDTO(rs.getString(1), rs.getDouble(2),
                        rs.getDouble(3))
        );
    }
}